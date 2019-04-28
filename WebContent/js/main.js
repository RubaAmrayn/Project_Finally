var routers = {
    routes: [
        {
            path: 'login',
            file: 'login.html'
        },
        {
            path: 'About',
            file: 'About.html'
        },
        {
            path: 'Groups',
            file: 'Groups.html'
        },
        {
            path: 'Home',
            file: 'Home.html'
        },
        {
            path: 'NewCode',
            file: 'NewCode.html'
        },
        {
            path: 'search',
            file: 'search.html'
        },
        {
            path: 'Rest',
            file: 'Rest.html'
        },
        {
            path: 'SharedCode',
            file: 'SharedCode.html'
        },
        {
            path: 'Signup',
            file: 'SignUp.html'
        },
   
        {
            path: 'viewCode',
            file: 'viewCode.html'
        },
        {
            path: 'ViewGroup',
            file: 'ViewGroup.html'
        }
    ],
    push: function (path,id) {
        window.location.hash = path
        if(id){
        	localStorage.setItem('Code_id',id);
        }
    }
}
function handleRoutes(myrouters) {
    let hash = window.location.hash.split('#')[1]
    var header = document.querySelector('header')
    console.log('handling new route')
    for (let i = 0; i < myrouters.routes.length; i++) {
        if (hash.toUpperCase() == myrouters.routes[i].path.toUpperCase()) {
            if(myrouters.routes[i].path.toUpperCase()=='LOGIN'||myrouters.routes[i].path.toUpperCase()=='SIGNUP'){
               
                  if(header!=null){
                      header.innerHTML = ''
                  }
                   //remove header
            }
            else{
              if(!!header.innerHTML == false){
                     
             
                axios.get('../myCode/navHeader.html').then(response=>{
                    header.innerHTML = response.data
                    var btn_search = document.querySelector('button#btn-search').addEventListener('click',search)
                 })
              }  
            }
           
            axios.get(`../myCode/${myrouters.routes[i].file}`).then(response => {
                document.querySelector('main#app').innerHTML = response.data
                var main = document.querySelector('script#main')
               if(!!document.querySelector('script#dynamic-script') == false){
            	   //create new script
            	  let script = document.createElement('script')
            	  script.src = `./js/${myrouters.routes[i].file.split('.')[0]}.js`
            	  script.id = "dynamic-script"	  
            	  main.parentNode.insertBefore(script,main.nextSibling)	  
            	  
               }else{//remove script
            	 let script = document.querySelector('script#dynamic-script')
            	 script.parentNode.removeChild(script);
            	 script.src = `./js/${myrouters.routes[i].file.split('.')[0]}.js`
               	 script.id = "dynamic-script"	  
               	 main.parentNode.insertBefore(script,main.nextSibling)	
               }
               
            })
            return
        }
    }//page Error
    axios.get('../myCode/404.html').then(response=>{
        document.querySelector('main#app').innerHTML = response.data
    })
   
}
if(window.location.hash ){
    handleRoutes(routers)
}else {
    window.location.hash="login"
}

(function(){//button Share
  
    var shareButtons = document.querySelector('a.share-btn');
  
    if (shareButtons) {
        [].forEach.call(shareButtons, function(button) {
        button.addEventListener("click", function(event) {
                   var width = 150,
                     height = 100;
  
          event.preventDefault();
  
          window.open(this.href, 'Share Dialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,width='+width+',height='+height+',top='+(screen.height/2-height/2)+',left='+(screen.width/2-width/2));
        });
      });
    }
  })();
window.addEventListener('DOMContentLoaded', handleRoutes(routers));
function search(){
	getResults().then(res=>{
	routers.push('search')
	var search_box = document.querySelector('div.row')
	for(let row of res.data.codes){
		search_box.innerHTML+=`
		   <div class="col-4" onclick="routers.push('viewCode',${row.Code_id})">
                <div class="front">
                    <header class="front-header">
                        <h3>${row.Title}</h3>
                    </header>
                    <p>${row.Code_desc}</p>
                </div>
            </div>`		
	}
	
});

}
//search.js
const getResults = ()=>{//
	return new Promise((resolve,reject)=>{
	 var search = document.querySelector('#search').value
	var user = simpleQueryString.stringify({
		 Title: search
	});

			const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	 axios.post('search',user,config).then(response=>{
		 resolve(response)
	 })
		
	})
}
