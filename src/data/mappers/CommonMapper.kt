package data.mappers

import data.dto.IdDto

object CommonMapper {

    fun toIdDto(id: String) = id.run {
        IdDto(
            id = this
        )
    }

    fun toId(idDto: IdDto) = idDto.id

}