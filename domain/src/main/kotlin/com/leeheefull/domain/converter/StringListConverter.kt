package com.leeheefull.domain.converter

import com.leeheefull.domain.util.JsonUtil
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(attribute: List<String>?) =
        JsonUtil.convertObjectListToString<String>(attribute)

    override fun convertToEntityAttribute(dbData: String?) =
        JsonUtil.convertJsonArrayToObjectList<String>(dbData)
}
