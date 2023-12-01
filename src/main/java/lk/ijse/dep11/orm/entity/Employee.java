package lk.ijse.dep11.orm.entity;

import lk.ijse.dep11.orm.annotation.Column;
import lk.ijse.dep11.orm.annotation.Id;
import lk.ijse.dep11.orm.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("employee")
public class Employee {
    @Id
    @Column
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "married_status", nullable = false)
    private boolean marriedStatus;
    @Column(name = "contact", nullable = false)
    private String contact;
}
