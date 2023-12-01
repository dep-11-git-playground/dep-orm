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
@Table("customer")
public class Customer {
    @Id
    @Column()
    private int id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String address;
}
