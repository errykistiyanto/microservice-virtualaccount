package co.id.middleware.router.domain;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Date;

/**
 * @author errykistiyanto@gmail.com 2020-07-12
 */
@Entity
@Table(name = "history")
public class History {

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", date=" + date +
                ", data='" + data + '\'' +
                ", rekon='" + rekon + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Type(type="text")
    private String data;
    @Type(type="text")
    private String rekon;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) { this.data = data; }

    public String getRekon() { return rekon; }

    public void setRekon(String rekon) { this.rekon = rekon; }

}
