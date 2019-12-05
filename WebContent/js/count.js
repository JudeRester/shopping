/**
 * 
 */
$(document).ready(function() {
	function getTimer(date, id) {
		var dday = new Date(date);

		function showRemaining() {
			var now = new Date();
			var distance = dday - now;
			var timer;

			var _second = 1000;
			var _minute = _second * 60;
			var _hour = _minute * 60;
			var _day = _hour * 24;

			if (distance < 0) {
				clearInterval(timer);
				document.getElementById(id).innerHTML="만료!";
				return;
			}

			var days = Math.floor(distance / _day);
			var hours = Math.floor((distance % _day) / _hour);
			var minutes = Math.floor((distance % _hour) / _minute);
			var seconds = Math.floor((distance % _minute) / _second);
			document.getElementById(id).innerHTML=days+"일"+hours+"시간"+minutes+"분"+seconds+"초";
		}
		timer=setInterval(showRemaining, 1000);
	};
	var date = new Date(2019, 11, 8);
	getTimer(date, "a");
	var date = new Date(2019, 11, 9);
	getTimer(date, "b");
	var date = new Date(2019, 11, 10);
	getTimer(date, "c");
	var date = new Date(2019, 11, 11);
	getTimer(date, "d");
	var date = new Date(2019, 11, 12);
	getTimer(date, "e");
	getTimer(new Date(), "f");
	
});