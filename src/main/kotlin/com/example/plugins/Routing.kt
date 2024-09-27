package com.example.plugins

import com.example.model.PointsCalculator
import com.example.model.Retailer
import com.example.model.RetailerId
import com.example.model.RetailerRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRouting() {
    routing {
        route("/receipts") {
            post("/process") {
                try {
                    val retailer = call.receive<Retailer>()

                    val id = UUID.randomUUID().toString()

                    RetailerRepository.add(retailer.copy(id = RetailerId(id)))

                    call.respond(mapOf("id" to id))
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            get("{id}/points") {
                val id = call.parameters["id"]

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val retailer = RetailerRepository.findById(RetailerId(id))

                if (retailer != null) {
                    val points = PointsCalculator().calculate(retailer)

                    call.respond(mapOf("points" to points))
                }
                else {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
            }
        }
    }
}