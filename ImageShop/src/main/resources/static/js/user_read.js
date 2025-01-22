let userReadObject = {
	//익명함수, 화살표함수
	init: function() {
		let _this = this;
		let formObj = $("#member");

		$("#btnEdit").on("click", function() {
			let userNo = $("#userNo");
			let userNoVal = userNo.val();
			self.location = "/user/modify?userNo=" + userNoVal;
		});

		$("#btnRemove").on("click", function() {
			formObj.attr("action", "remove");
			formObj.submit();
		});

		$("#btnList").on("click", function() {
			self.location = "list";
		});

		//서버에 ajax로 보내서 리턴값을 받는 경우
		$("#btnAjax").on("click", function() {
			_this.listRefly();
		});
	},
	
	listRefly : function(){
		let userNo = $("#userNo").val();
		alert('내용리스트 요청됨' + userNo);
		
		/*$.ajax({
			type: "GET",
			url: "/user/member/"+userNo
		}).done(function(response){
			let message = response["dataList"]
			alert(message);
			location = "/user"
		}).fail(function(error){
			alert("error 발생"+error);
			location= "/";
		});*/
	}
};

userReadObject.init();

