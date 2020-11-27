package hu.bme.aut.freelancerandroid.repository.converter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalTime

class TimeConverter : TypeAdapter<LocalTime>() {
    override fun write(out: JsonWriter?, value: LocalTime?) {
        if (out != null && value != null) {
            out.beginObject()
            out.name("startTime")
            out.value("${value.hour}:${value.minute}:${value.second}")
            out.endObject()
        }
    }

    override fun read(reader: JsonReader?): LocalTime {
        var time = LocalTime.of(10, 10)
        if (reader != null) {
//            reader.beginObject()
            val timeParts = reader.nextString().split(":")
            time = LocalTime.of(
                Integer.parseInt(timeParts[0]),
                Integer.parseInt(timeParts[1]),
                Integer.parseInt(timeParts[2])
            )
        }
        return time
    }
}