<html>
<body>

<div class="container">
    <div class="row">
        <label> ${sessions}
        <div class="col-sm-12 content">
            <h4 class="text-center">Тестовый сервер для табло</h4>
            <br>
            <br>
            <form id="form" class="form-inline text-center" method="post" modelAttribute="message" action="football"
                  name="sendForm">
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="sendButton" id="send"
                           value="Футбол">
                </div>
            </form>
            <br>
            <br>
            <form id="form" class="form-inline text-center" method="post" modelAttribute="message" action="tenis"
                  name="sendForm">
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="sendButton" id="send"
                           value="Тенис">
                </div>
            </form>
            <br>
            <br>
            <form id="form" class="form-inline text-center" method="post" modelAttribute="message" action="logo"
                  name="sendForm">
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="sendButton" id="send"
                           value="Лого">
                </div>
            </form>
            <br>
            <br>
            <form id="form" class="form-inline text-center" method="post" modelAttribute="message" action="winner"
                  name="sendForm">
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="sendButton" id="send"
                           value="Победитель">
                </div>
            </form>
        </div>
    </div>

</body>
</html>