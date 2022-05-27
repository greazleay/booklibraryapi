package greazleay.booklibraryapi.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "genre")
class Genre (

    @Column(name = "name", nullable = false)
    var name: String,
    
    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "books")
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "genres")
    var books: MutableSet<Book>
) {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", nullable = false)
    var id: String? = null

    @Column(name = "created_at")
    var createdAt: Date = Date()

    @Column(name = "updated_at")
    var updatedAt: Date = Date()

}