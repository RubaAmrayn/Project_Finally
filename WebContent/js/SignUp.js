var btnLogin = document.querySelector('button#btn-signup');
btnLogin.addEventListener('click',function(e){
   e.preventDefault();
   var firstName=document.querySelector('input#firstName').value
   var lastName=document.querySelector('input#lastName').value
   var user=document.querySelector('input#username').value
   var email=document.querySelector('input#email').value
   var pass=document.querySelector('input#password').value
   var repPass=document.querySelector('input#repPass').value
  
   
   var user = simpleQueryString.stringify({//object 
			firstName:firstName,
			lastName:lastName,
			user :user,
			email:email,
			pass: pass
			
        })
   const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}//validation
     var letter = /^[A-Za-z]+$/;
     var letters = /^[A-Za-z0-9]+$/;
     var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
     var firstTest=letter.test(firstName);
     var lastTest=letter.test(lastName);
     var userTest=letters.test(user);
     var emailTest=mailformat.test(email)
     if(!firstTest){
    	 alert('firstName invalid using just char');
    	 return false;
     }
     else if(!lastTest){
    	 alert('lastName wrong invalid using just char');
    	 return false;
     }
     else if(!!userTest){
    	 alert('user invalid');
    	 return false;
     }
     else if(!emailTest){
    	 alert('email not valid');
    	 return false;
     }
     else if(pass != repPass ){
    	 alert('password not matches');
    	 return false;
     }
     else{
    	 axios.post('SignUp',user,config).then(response=>{
  		   
  		   if(!!response.data.user_id){
  				routers.push('Home')//call another page
  			}
  	   })
     }

})
const setUserID =id=>{
return new Promise((resolve,reject)=>{
  if(id){//store in Permanent memory Success status 
      window.localStorage.setItem('user_id',id)
      resolve()
  }else{//Failure status
      reject('حصل خطأ في تخزين رقم المستخدم')
  }
})
}


