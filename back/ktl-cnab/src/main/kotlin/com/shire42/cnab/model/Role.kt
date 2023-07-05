package com.shire42.cnab.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cnab_role")
class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id")
    var id: UUID? = null
        set(id) {
            field = this.id
        }

    @Column(name = "role_name")
    var roleName: String? = null
        set(roleName) {
            field = this.roleName
        }

    @Column(name = "description")
    var description: String? = null
        set(description) {
            field = this.description
        }
}