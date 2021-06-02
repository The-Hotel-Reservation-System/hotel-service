import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return list roomDto model when get hotel room"
    request {
        method 'GET'
        url ("/api/hotel/1/room")
    }
    response {
        body([
                [id: 5,
               type: "ROOM_TYPE",
               status: "AVAILABLE",
               hotel: "hophanminh121212232323",
               price: "1300"]
        ])
        headers {
            contentType applicationJson()
        }
        status 200
    }
}

