package lol.cloud.lolcloud.s3.bucket.domain.bucket_object

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import java.time.LocalDateTime

@Entity
class BucketObject(

    var objectName: String,

    @Enumerated(EnumType.STRING)
    val objectType: ObjectType,
    var prefix: String,
    var objectSize: Long? = null,
    val createDate: LocalDateTime? = null,
    val modifyDate: LocalDateTime? = null,
    val objectExt: String? = null,
    var key: String? = null,
    var objectUrl: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_bucket_object_id", referencedColumnName = "bucket_object_id")
    var parent: BucketObject? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    val children: List<BucketObject> = emptyList(),

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    val bucket: Bucket,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_object_id")
    val id: Long? = null,
) {

    fun toDto(): BucketObjectResponse {
        return BucketObjectResponse(
            prefix = prefix,
            objectName = objectName,
            objectType = objectType.name,
            id = id!!,
            createDate = createDate,
            modifyDate = modifyDate,
            objectSize = objectSize,
            key = key,
            objectExt = objectExt,
            bucketName = bucket.bucketName
        )
    }

    fun addParent(parent: BucketObject) {
        this.parent = parent
    }

    fun createObjectUrl() {
        objectUrl = "localhost:8080${bucket.bucketName}/${prefix}${objectName}"
    }

    fun createBucketObjectKey() {
        key = prefix + objectName
    }

    fun addDetailObjectName() {
       if(objectType == ObjectType.FOLDER) {
           objectName = "$objectName/"
        }
    }

    fun getAllChildren(): List<BucketObject> {

        val allChildren = mutableListOf<BucketObject>()
        allChildren.add(this)

        if(children.isNotEmpty()){
            getChildren(this, allChildren)
            allChildren.sortByDescending { it.id }
        }

        return allChildren
    }

    fun getChildren(bucketObject: BucketObject, childrens: MutableList<BucketObject>) {
        bucketObject.children.forEach {
            childrens.add(it)

            if(it.children.isNotEmpty()){
                getChildren(it, childrens)
            }

        }
    }


}