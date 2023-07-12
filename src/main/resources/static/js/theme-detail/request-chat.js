window.addEventListener("load", () => {
    const chatRequestBtn = document.querySelector(".theme__detail__chat-request");

    chatRequestBtn.addEventListener("click", function(e) {
        if(chatRequestBtn.classList.contains("not-logined"))
            return;

        e.preventDefault();

        (async () => {
            const {travelThemeId, travelerId, guideId} = chatRequestBtn.dataset;
            const chatRoom = {
                travelThemeId,
                travelerId,
                guideId
            };

            let result = null;
            {
                let response = await fetch("/api/chatRooms", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(chatRoom)
                })
                
                result = await response.json();
            }

            if(result) {
                let chatRoomId = result.id;

                const notice = {
                    chatRoomId,
                    travelerId,
                    guideId
                };

                let response = await fetch("/api/notices", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(notice)
                })

                console.log(response.json());
            }

        }) ();

    })
})