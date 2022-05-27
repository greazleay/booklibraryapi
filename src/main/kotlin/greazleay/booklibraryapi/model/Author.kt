package greazleay.booklibraryapi.model

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity(name = "author")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
class Author (

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "biography")
    var biography: String = "",

    @Column(name = "birth_date")
    var birthDate: Date? = null,

    @Column(name = "death_date")
    var deathDate: Date? = null,

//    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = [CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST])
    var books: MutableSet<Book>? = null,

    @Column(name = "image_url")
    var imageUrl: String? = null,

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