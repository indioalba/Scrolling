package com.example.scrolling.ui.list.model

import com.google.gson.annotations.SerializedName

//data class UserResponse(@SerializedName("_meta") val meta: Meta, @SerializedName("result") val userList: List<User>)

data class UserResponse(@SerializedName("result") val userList: List<User>)

data class Meta(val success:Boolean,
                val code:Int,
                val message: String,
                val totalCount: Int,
                val pageCount:Int,
                val currentPage: Int,
                val perPage: Int,
                val rateLimit: RateLimit)

data class RateLimit(val limit:Int,
                     val remaining:Int,
                     val reset:Int)

/*data class User(val id: Int,
                @SerializedName("first_name") val firstName: String,
                @SerializedName("last_name") val lastName: String,
                val gender: Gender,
                val dob: String,
                val email: String,
                val phone: String,
                val website: String,
                val address: String,
                val status: Status)
*/


data class User(val id: Int,
                @SerializedName("first_name") val firstName: String,
                @SerializedName("last_name") val lastName: String,
                val gender: Gender)

enum class Gender(val value: String){
 MALE("male"),
 FEMALE("female")
}

enum class Status(val status: String) {
 ACTIVE("active"),
 INACTIVE("inactive")
}

/*
_meta>
<success>true</success>
<code>200</code>
<message>OK. Everything worked as expected.</message>
<totalCount>2226</totalCount>
<pageCount>112</pageCount>
<currentPage>1</currentPage>
<perPage>20</perPage>
<rateLimit>
<limit>30</limit>
<remaining>29</remaining>
<reset>2</reset>
</rateLimit>
</_meta>

<result>
<item>
<id>1329</id>
<first_name>ANIL</first_name>
<last_name>MOHNANI</last_name>
<gender>male</gender>
<dob>1978-08-24</dob>
<email>anil@mohnani.com</email>
<phone>513-367-1499</phone>
<website>http://jerde.com/</website>
<address>27384 Coleman View New Anitastad, NH 79514</address>
<status>active</status>
<_links>
<self>
<href>https://gorest.co.in/public-api/users/1329</href>
</self>
<edit>
<href>https://gorest.co.in/public-api/users/1329</href>
</edit>
<avatar>
<href>https://lorempixel.com/250/250/people/?34390</href>
</avatar>
</_links>
</item>

*/
