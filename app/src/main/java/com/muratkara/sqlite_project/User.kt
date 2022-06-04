package com.muratkara.sqlite_project

 class User {
    var id : Int = 0
    var nameSurname : String = ""
    var age : Int = 0
    var country : String = ""

    constructor(nameSurname:String , age:Int , country:String){
        this.nameSurname = nameSurname
        this.age = age
        this.country = country
    }
    constructor(){

    }
}