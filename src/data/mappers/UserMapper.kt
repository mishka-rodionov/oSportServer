package data.mappers

import data.dto.requests.UserRequest
import data.models.User

object UserMapper {

    fun toModel(userRequest: UserRequest, userId: String) = userRequest.run{
        User(
            id = userId,
            firstName, middleName, lastName, phoneCountryPrefix, phoneNumber, email
        )
    }

}