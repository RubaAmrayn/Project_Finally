
var btnLogin = document.querySelector('button#btn-login');

btnLogin.addEventListener('click',function(e){
   e.preventDefault();
   var pass=document.querySelector('input#password').value
   var user=document.querySelector('input#username').value
   var user = simpleQueryString.stringify({//convert object to 'application/x-www-form-urlencoded'
	   user:user,
	   pass:pass
   })
   const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}  //function Validate
	    var un = document.getElementById('username').value;
	    var pw = document.getElementById('password').value;
	     if(!!un==false || !!pw==false){
	      alert('should not empty');
	      return false;
	    }
	    else {
	          axios.post('Login',user,config).then(response=>{
	          if(!!response.data.users[0]){
	     		setUserID(response.data.users[0].user_id).then(()=>{
				routers.push('Home')
			})
		}
   })
   
	    }
});
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