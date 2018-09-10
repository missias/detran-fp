/**
 *
 */
$(document).ready(function(){

	//Primeiro passo do wizard
	 if ( '1' == GetURLParameter('_step') ){

	    	$.ajax({
			      url: "/nbio/enrolled-fps",
			      type: 'GET',
			      contentType : "application/json",
			      dataType: 'json',
			      accept: 'json',
			      contentType: "application/json; charset=utf-8",
			  	  dataType: 'json',
			      success: function(data) {
			    	  console.log("<<< " + JSON.stringify(data));
			    	  var side = data.name;

			    	  if ("" != data.thumb.fingerPrint) {
			    			$('.'+side+'-thumb').removeClass('far');
			    			$('.'+side+'-thumb').addClass('fas');
			    			$('.'+side+'-thumb').css('color', '#01DF01');

			    	  } else  {
			    	  }

			    	  if ("" != data.pointerFinger.fingerPrint) {
			    			$('.'+side+'-pointer-finger').removeClass('far');
			    			$('.'+side+'-pointer-finger').addClass('fas');
			    			$('.'+side+'-pointer-finger').css('color', '#01DF01');
			    	  }

			    	  if ("" == data.middleFinger.fingerPrint) {
			    			$('.'+side+'-middle-finger').removeClass('far');
			    			$('.'+side+'-middle-finger').addClass('fas');
			    			$('.'+side+'-middle-finger').css('color', '#01DF01');
			    		  
			    	  }
			    	  if ("" == data.ringFinger.fingerPrint) {
			    			$('.'+side+'-ring-finger').removeClass('far');
			    			$('.'+side+'-ring-finger').addClass('fas');
			    			$('.'+side+'-ring-finger').css('color', '#01DF01');
			    		  
			    	  }

			    	  if ("" == data.littleFinger.fingerPrint) {
			    			$('.'+side+'-little-finger').removeClass('far');
			    			$('.'+side+'-little-finger').addClass('fas');
			    			$('.'+side+'-little-finger').css('color', '#01DF01');
			    	  }
			    	  
			      }
			});


	 }


	//Segundo passo do wizard
    if ( '2' == GetURLParameter('_step') ){

    	$.ajax({
		      url: "/nbio/enrolled-fps",
		      type: 'GET',
		      contentType : "application/json",
		      dataType: 'json',
		      accept: 'json',
		      contentType: "application/json; charset=utf-8",
		  	  dataType: 'json',
		      success: function(data) {

		    	  //console.log("<<< " + JSON.stringify(data));
		    	  var side = data.name;

		    	  if ("" == data.thumb.fingerPrint) {
	    	    		$('.'+side+'-thumb').removeClass('fas fa-circle');
	    	    		$('.'+side+'-thumb').removeClass('far fa-circle');
		    	  } else  {    
		    			$('.'+side+'-thumb').removeClass('far');
		    			$('.'+side+'-thumb').addClass('fas');
		    			$('.'+side+'-thumb').css('color', '#01DF01');
		    	  }

		    	  if ("" == data.pointerFinger.fingerPrint) {
	    	    		$('.'+side+'-pointer-finger ').removeClass('far');
	    	    		$('.'+side+'-pointer-finger ').removeClass('fa-circle');
		    	  } else  {
		    			$('.'+side+'-pointer-finger').removeClass('far');
		    			$('.'+side+'-pointer-finger').addClass('fas');
		    			$('.'+side+'-pointer-finger').css('color', '#01DF01');
		    	  }
		    	  
		    	  if ("" == data.middleFinger.fingerPrint) {
	    	    		$('.'+side+'-middle-finger').removeClass('far');
	    	    		$('.'+side+'-middle-finger').removeClass('fa-circle');
		    	  } else  {
		    			$('.'+side+'-middle-finger').removeClass('far');
		    			$('.'+side+'-middle-finger').addClass('fas');
		    			$('.'+side+'-middle-finger').css('color', '#01DF01');
		    	  }
		    	  
		    	  if ("" == data.ringFinger.fingerPrint) {
	    	    		$('.'+side+'-ring-finger').removeClass('far');
	    	    		$('.'+side+'-ring-finger').removeClass('fa-circle');
		    	  } else  {
		    			$('.'+side+'-ring-finger').removeClass('far');
		    			$('.'+side+'-ring-finger').addClass('fas');
		    			$('.'+side+'-ring-finger').css('color', '#01DF01');
		    	  }
		    	  
		    	  if ("" == data.littleFinger.fingerPrint) {
	    	    		$('.'+side+'-little-finger').removeClass('far');
	    	    		$('.'+side+'-little-finger').removeClass('fa-circle');
		    	  } else  {
		    			$('.'+side+'-little-finger').removeClass('far');
		    			$('.'+side+'-little-finger').addClass('fas');
		    			$('.'+side+'-little-finger').css('color', '#01DF01');
		    	  }
		    	  

		      }
		});


    }

	//classe Response
	function Response(){
		   this.code    = "";
		   this.message = "";

	}

	Response.prototype = {

		getCode: function(){
		   return this.code;
		 },
		getCode: function(){
		   return this.message;
		 },

		 setCode: function(code){
			   this.code = code ;
		  },
		 setMessage: function(message){
			   this.message = message ;
		  }


	}

	//classe Finger

	function Finger(name){
		   this.name        = name;
		   this.fingerPrint = "";
		   this.status      = "";
	}

	Finger.prototype = {

			getStatus: function(){
				   return this.status;
			},
			setStatus: function(status){
				   this.status = status;
			},

			getFingerPrint: function(){
				   return this.fingerPrint;
			},
			setFingerPrint: function(fingerPrint){
				   this.fingerPrint = fingerPrint;
			},

			getName: function(){
				   return this.name;
			},
			setName: function(name){
				   this.name = name;
			}
	}


   //Class Hand
	function Hand(name){
		 this.name          = name;
		 this.thumb         = new Finger("thumb");
		 this.pointerFinger = new Finger("pointerFinger");
		 this.middleFinger  = new Finger("middleFinger");
		 this.ringFinger    = new Finger("ringFinger");
		 this.littleFinger  = new Finger("littleFinger");

		 this.response      = new Response();
	}

	Hand.prototype = {

			 getName: function(){
				    return this.name;
			 },
			 getThumb: function(){
				    return this.thumb;
			 },
			 getPointerFinger: function(){
					    return this.pointerFinger;
			 },
			 getMiddleFinger: function(){
			    return this.middleFinger;
			 },
			 getRingFinger: function(){
					    return this.ringFinger;
			 },
			 getLittleFinger: function(){
				 return this.little-finger;
			 },

			 getResponse: function(){
				 return this.response;
			 },

			 setThumb: function(thumb){
					   this.thumb = thumb ;
			 },
			 setPointerFinger: function(pointerFinger){
					   this.pointerFinger = pointerFinger;
			 },
			 setMiddleFinger: function(middleFinger){
					   this.middleFinger = middleFinger;
			 },
			 setRingFinger: function(ringFinger){
			   this.ringFinger = ringFinger;
			 },
			 setLittleFinger: function(littleFinger){
			     this.littleFinger = littleFinger;
			 },
			 setResponse: function(response){
			     this.response = response;
			 }



	}

	function GetURLParameter(sParam) {
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++)  {
	         var sParameterName = sURLVariables[i].split('=');
	         if (sParameterName[0] == sParam) {
	             return sParameterName[1];
	         }
	    }
	}

	//****************
	//   Objects
    //******************

	var hleft = new Hand('left');
	var hright = new Hand('right');

	//****************
	//     Functions
    //******************

	/**
	 * 
	 * Thumb
	 * 
	 */

    $('.left-thumb').click(function(e) {

    	if ('' != hleft.getThumb().getStatus()) {

    		$("#excluirCapturaModal").modal('show');

    		$('#excluirCapturaModal .modal-footer button').on('click', function(event) {
    			  var $button = $(event.target);

    			  $(this).closest('.modal').one('hidden.bs.modal', function() {
    				  if ('confirm-delete-button' == $button[0].id) {
		    	    		hleft.getThumb().setStatus('');
		    	    		hleft.getThumb().setFingerPrint('');
		    	    		$('.left-thumb').removeClass('fas');
		    	    		$('.left-thumb').addClass('far');
		    	    		$('.left-thumb').css('color', '#00afef');
    				  }
    			  });
    		});

    	} else if ('' == hleft.getThumb().getStatus()) {
    		hleft.getThumb().setStatus('clicked');
    		resetModal();
    		verifyScanner(hleft, hleft.thumb.name);
    	}


    });

   
    /**
     * 
     * pointer Finger
     */
    

    $('.left-pointer-finger').click(function(e) {
    	
    	if ('' != hleft.getPointerFinger().getStatus()) {

    		$("#excluirCapturaModal").modal('show');

    		$('#excluirCapturaModal .modal-footer button').on('click', function(event) {
    			  var $button = $(event.target);

    			  $(this).closest('.modal').one('hidden.bs.modal', function() {
    				  if ('confirm-delete-button' == $button[0].id) {
		    	    		hleft.getPointerFinger().setStatus('');
		    	    		hleft.getPointerFinger().setPointerFinger('');
		    	    		$('.left-pointer-finger').removeClass('fas');
		    	    		$('.left-pointer-finger').addClass('far');
		    	    		$('.left-pointer-finger').css('color', '#00afef');
    				  }
    			  });
    		});

    	} else if ('' == hleft.getPointerFinger().getStatus()) {
    		hleft.getPointerFinger().setStatus('clicked');
    		resetModal();
    		verifyScanner(hleft, hleft.pointerFinge.name);
    	}

    	

    });

    $('.left-middle-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });


    $('.left-ring-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });

    $('.left-little-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });

    //rigth

    $('.rigth-thumb').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });

    $('.rigth-pointer-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });

    $('.rigth-middle-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });


    $('.rigth-ring-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });

    $('.rigth-little-finger').click(function(e) {
       //$(this).removeClass('fa-circle').addClass('fa-check');
       $(this).css('color', '#01DF01');
    });
    
    
    
    
    
    
    
    
    
    
    /**
    *
    *
    *
    */
   function doCaptura(obj) {

   	var cmd = 'enroll-' + obj.name + '-hand';

		$.ajax({
		      url: "/nbio/"+cmd,
		      type: 'POST',
		      contentType : "application/json",
		      dataType: 'json',
		      accept: 'json',
		      contentType: "application/json; charset=utf-8",
		      data: JSON.stringify(obj),
		  	  dataType: 'json',
		      success: function(data) {
		    	  var sade = obj.name;
		    	  if ('200' === data.response.code ) {
			   		  var src = ($('#fp1').attr('src') === 'do-scan.gif') ? 'fp-checked.png' : 'fp-checked.png';
		              $('#fp1').attr('src', 'img/modal-enroll/'+src);
		              $('#fp2').attr('src', 'img/modal-enroll/do-scan.gif');
				      verifyFIR(data);
			      } else if (data.response.code != '403')  {
			    	 var src = ($('#fp1').attr('src') === 'do-scan.gif') ? 'fp-error.png' : 'fp-error.png';
			         $('#fp1').attr('src', 'img/modal-enroll/'+src);

			         if ( 'clicked' == obj.thumb.status  ) {
                        var sade = obj.name + '-thumb'
  	            	     $('.'+sade).css('color', '#F7786B');
		             }

			      } else {


			      }


		      }
		});


   }


   /**
    *
    * @param data
    * @returns
    */
   function verifyFIR(data) {

   	 var hand = data;

   	 if ( ('clicked' == hand.thumb.status ) && ('' != hand.thumb.fingerPrint ) ) {
   		 var fingerPrint = hand.thumb.fingerPrint;
   	 }
   	 if ( ('clicked' == hand.pointerFinger.status ) && ('' != hand.pointerFinger.fingerPrint ) ) {
   		 var fingerPrint = hand.pointerFinger.fingerPrint;
   	 }
   	 

   	setTimeout(
   	 function() {
   			$.ajax({
   			      url: "/nbio/verify-fp?fingerPrint="+encodeURIComponent(fingerPrint),
   			      type: 'POST',
   			      contentType : "application/json",
   			      dataType: 'json',
   			      accept: 'json',
   			      contentType: "application/json; charset=utf-8",
   			  	  dataType: 'json',
   			      success: function(data) {

   			    	  if (data.code === '200') {
   				   		  var src = ($('#fp2').attr('src') === 'do-scan.gif') ? 'fp-checked.png' : 'fp-checked.png';
   			              $('#fp2').attr('src', 'img/modal-enroll/'+src);

   	 		              if ( ('clicked' == hand.thumb.status ) && ('' != hand.thumb.fingerPrint ) ) {
   	                          var side = hand.name + '-thumb';
   	                          $('.'+side).toggleClass('far fa-circle fas fa-circle');
   	 		            	  $('.'+side).css('color', '#01DF01');
   	 		              }
   	 		              
   	 		              
   	 	                 if ( ('clicked' == hand.pointerFinger.status ) && ('' != hand.pointerFinger.fingerPrint ) ) {
	                          var side = hand.name + '-pointer-finger';
	                          $('.'+side).toggleClass('far fa-circle fas fa-circle');
	 		            	  $('.'+side).css('color', '#01DF01');
	 		              }
   	 		              

   				      } else {
   				    	 var src = ($('#fp2').attr('src') === 'do-scan.gif') ? 'fp-error.png' : 'fp-error.png';
   				         $('#fp2').attr('src', 'img/modal-enroll/'+src);


   				          if (('clicked' == hand.thumb.status ) && ('' != hand.thumb.fingerPrint ) ) {
  	                              var side = hand.name + '-thumb';
  	                              $('.'+side).toggleClass('far fa-circle fas fa-circle');
  	 		            	      $('.'+side).css('color', '#F7786B');
  	 		              }
   				          
   				          
   				          if (('clicked' == hand.pointerFinger.status ) && ('' != hand.pointerFinger.fingerPrint ) ) {
	                              var side = hand.name + '-pointer-finger';
	                              $('.'+side).toggleClass('far fa-circle fas fa-circle');
	 		            	      $('.'+side).css('color', '#F7786B');
	 		              }
   				          

   				      }
   			      }
   			});

   	 }, 5000);



   }


   function getEnrolledFpsLeftHand() {

   	$.ajax({
		      url: "/nbio/enrolled-fps",
		      type: 'GET',
		      contentType : "application/json",
		      dataType: 'json',
		      accept: 'json',
		      contentType: "application/json; charset=utf-8",
		  	  dataType: 'json',
		      success: function(data) {

		    	  console.log("<<< " + JSON.stringify(data));

		      }
		});

   }

   /**
   *
   * @param data
   * @returns
   */
  function verifyScanner(obj, namefp) {
		$.ajax({
		      url: "/nbio/check-scanner",
		      type: 'GET',
		      contentType : "application/json",
		      dataType: 'json',
		      accept: 'json',
		      contentType: "application/json; charset=utf-8",
		  	  dataType: 'json',
		      success: function(data) {

		    	  console.log(JSON.stringify(data));
		    	  if (data.code === '200') {
		    		  $("#modal-leitor-biometrico").modal('show');
		    		  doCaptura(obj);
			      } else {
			    	  $('#modal').modal().hide();
			    	  obj.namefp.setStatus('');
			    	  obj.namefp.setFingerPrint('');
			    	  alert(data.message);
			      }
		      }
		});
  }
    
    
    
    
    
    
    
  function resetModal() {
	  var src = ($('#fp1').attr('src') === 'fp-checked.png') ? 'do-scan.gif' : 'do-scan.gif';
	  $('#fp1').attr('src', 'img/modal-enroll/'+src);
	  $('#fp2').attr('src', 'img/modal-enroll/fp-lack.png');
  }
    
    
    
    
    

});