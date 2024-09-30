package lol.cloud.lolcloud.s3.bucket.domain

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType

class BucketObject(
    val bucketName: String,
    var objectName: String,
    val objectType: ObjectType,
    val prefix: String = "",
    val objectSize: Long = 0L,
    val parentId: Long? = null,
    var objectUrl: String,
    var bucketObject: BucketObject?,
    var key: String
) {

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

    fun isFolder(): Boolean {
        return objectType == ObjectType.FOLDER
    }
}