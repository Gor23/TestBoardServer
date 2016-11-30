<html>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12 content">
            <h4 class="text-center">Тест табло</h4>
            <br>
            <h4 class="text-center">"{"type":"MESSAGE","period":null,"team_name_first":"Cats","team_name_second":"Raccoons","score_first":1,"score_second":6,"action_type":"GOAL"}"</h4>
            <br>
            <form  id="form" class="form-inline text-center" method="post" modelAttribute="message" action="/" name="send">
                <div class="form-group">
                    <input type="text" class="form-control" name="message" id="message" placeholder="Сообщение"/>
                </div>
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit"  name="find" id="send" value="Отослать" >
                </div>
                <br>
                <br>
            </form>
        </div>
        <div class="col-sm-6 content">
</body>
</html>