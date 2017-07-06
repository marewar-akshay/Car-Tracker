package io.egen.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Embeddable
public class Tire {

//    @Id
//    private String id;
    @Column(nullable = true)
    private Integer frontLeft;
    @Column(nullable = true)
    private Integer frontRight;
    @Column(nullable = true)
    private Integer rearLeft;
    @Column(nullable = true)
    private Integer rearRight;


    public Tire(){
    //    this.id = UUID.randomUUID().toString();
    }

/*    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

*/

    public Integer getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(Integer frontLeft) {
        this.frontLeft = frontLeft;
    }

    public Integer getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(Integer frontRight) {
        this.frontRight = frontRight;
    }

    public Integer getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(Integer rearLeft) {
        this.rearLeft = rearLeft;
    }

    public Integer getRearRight() {
        return rearRight;
    }

    public void setRearRight(Integer rearRight) {
        this.rearRight = rearRight;
    }
}
