/**
 *
 */

$(document).ready(function(){

	$.ajax({
	      url: "/nbio/enrolled-fps",
	      type: 'GET',
	      contentType : "application/json",
	      dataType: 'json',
	      accept: 'json',
	      contentType: "application/json; charset=utf-8",
	      data: JSON.stringify(obj),
	  	  dataType: 'json',
	      success: function(data) {
	    	  console.log(JSON.stringify(data));
	    	  if (data.response.code === '200') {
		   		  var src = ($('#fp1').attr('src') === 'do-scan.gif') ? 'fp-checked.png' : 'fp-checked.png';
	              $('#fp1').attr('src', 'img/modal-enroll/'+src);
			      $('.left-thumb').css('color', '#01DF01');

			     $('#fp2').attr('src', 'img/modal-enroll/do-scan.gif');

			     verifyFIR(data);

		      } else if (data.response.code != '403')  {
		    	 var src = ($('#fp1').attr('src') === 'do-scan.gif') ? 'fp-error.png' : 'fp-error.png';
		         $('#fp1').attr('src', 'img/modal-enroll/'+src);
			     $('.left-thumb').css('color', '#F7786B');
		      } else {


		      }


	      }
	});

});