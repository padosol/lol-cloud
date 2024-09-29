package lol.cloud.lolcloud.s3.bucket.domain

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType

class BucketObject(

    private val bucketName: String,
    private var objectName: String,
    private val objectType: ObjectType,
    private val prefix: String = "",
    private val objectSize: Long = 0L,
    private val parentId: Long? = null,

    private var objectUrl: String,
    private var bucketObject: BucketObject?,
    private var key: String
) {

    fun getBucketName(): String {
        return bucketName
    }

    fun getObjectType(): ObjectType {
        return objectType
    }

    fun getObjectName(): String {
        return objectName
    }

    fun getPrefix(): String {
        return prefix
    }
    fun addParent(parent: BucketObject) {
        this.bucketObject = bucketObject
    }


    fun createObjectUrl() {
        objectUrl = "localhost:8080/${bucketName}/${prefix}${objectName}"
    }

    fun createBucketObjectKey() {
        key = prefix + objectName
    }

    fun addDetailObjectName() {
        if(objectType == ObjectType.FOLDER) {
            objectName = "$objectName/"
        }
    }

//    fun getAllChildren(): List<BucketObject> {
//
//        val allChildren = mutableListOf<BucketObject>()
//        allChildren.add(this)
//
//        if(children.isNotEmpty()){
//            getChildren(this, allChildren)
//            allChildren.sortByDescending { it.id }
//        }
//
//        return allChildren
//    }

    fun getChildren(bucketObjectEntity: BucketObjectEntity, childrens: MutableList<BucketObjectEntity>) {
        bucketObjectEntity.children.forEach {
            childrens.add(it)

            if(it.children.isNotEmpty()){
                getChildren(it, childrens)
            }

        }
    }
}