package co.id.middleware.router.iso.virtualaccount;

import co.id.middleware.router.MicroserviceVirtualAccountApplication;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.q2.iso.QMUX;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.space.SpaceUtil;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.NameRegistrar;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author errykistiyanto@gmail.com 2020-07-12
 */
  public class Payment implements TransactionParticipant, Configurable

  {
      private Configuration cfg;
      private String isorequest;
      private String isosource;
      private String appname;
      private String mux;
      private String logger;
      private Long timeout;
      private MicroserviceVirtualAccountApplication application = null;

      @Override
      public void setConfiguration(Configuration configuration) throws ConfigurationException {
          this.cfg = configuration;
          this.isorequest = cfg.get("isorequest");
          this.isosource = cfg.get("isosource");
          this.logger = cfg.get("logger");
          this.appname = cfg.get("appname");
          this.mux = cfg.get("mux");
          this.timeout = cfg.getLong("timeout");
      }

      @Override
      public int prepare(long l, Serializable serializable) {
          return PREPARED | READONLY;
      }

      @Override
      public void commit(long l, Serializable serializable) {

          Space psp    = SpaceFactory.getSpace("jdbm:myspace");
          final String TRACES = "JPTS_TRACE";
          long traceNumber = SpaceUtil.nextLong(psp, TRACES) % 100000;

          ISOMsg m = (ISOMsg) ((Context) serializable).get(this.isorequest);
          ISOSource source = (ISOSource) ((Context)serializable).get(this.isosource);

          DecimalFormat df = new DecimalFormat("#,###");
          DecimalFormatSymbols dfs = new DecimalFormatSymbols();
          dfs.setCurrencySymbol("");
          df.setDecimalFormatSymbols(dfs);

          try {

              ISOMsg f = new ISOMsg();
              f.setMTI("0200");
              f.set(2,m.getString(2));
              f.set(3,m.getString(3));
              f.set(4,m.getString(4));
              f.set(7,m.getString(7));
              f.set(11,m.getString(11));
              f.set(12,m.getString(12));
              f.set(13,m.getString(13));
              f.set(14,m.getString(14));
              f.set(15,m.getString(15));
              f.set(18,m.getString(18));
              f.set(32,m.getString(32));
              f.set(33,m.getString(33));
              f.set(37,m.getString(37));
              f.set(41,m.getString(41));
              f.set(42,m.getString(42));
              f.set(43,m.getString(43));
              f.set(48,m.getString(48));
              f.set(49,m.getString(49));
              f.set(59,m.getString(59));
              f.set(62,m.getString(62));
              f.set(63,m.getString(63));
              f.set(98,m.getString(98));
              f.set(105,m.getString(105));
              f.set(106,m.getString(106));
              f.set(107,m.getString(107));


              QMUX qmux = (QMUX) NameRegistrar.get("mux." + this.mux);
              ISOMsg resp = qmux.request(f, this.timeout);


          if(resp != null){
              StringBuffer resp48 = new StringBuffer();
              switch (resp.getString(39)) {
                case "00":

                m.set(4,resp.getString(4));
                m.set(48, resp.getString(48));
                m.set(59,resp.getString(59));
                m.set(61,resp.getString(61));

                break;

            }
                m.set(39,resp.getString(39));
                m.set(61,resp.getString(61));
                m.set(63,resp.getString(63));
                m.set(105,resp.getString(105));
                m.set(106,resp.getString(106));
                m.set(107,resp.getString(107));

              } else {

                m.set(39,"68");
                m.set(61,"Transaction TimeOut");

              }

              m.setResponseMTI();
              source.send(m);

          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      @Override
      public void abort(long l, Serializable serializable) {

      }
  }