
	
const getGroups = ()=>{//
	return new Promise((resolve,reject)=>{
	 var user_id = localStorage.getItem('user_id')
	var user = simpleQueryString.stringify({
		 user_id: user_id
	});

			const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	 axios.post('Groups',user,config).then(response=>{
		 resolve(response)
	 })
		
	})
}

getGroups().then(res=>{
	var group_box = document.querySelector('div.group-box')
	for(let row of res.data.Groups){
		 group_box.innerHTML+=`
		    <div class="col-4">
                        <div class="group" onclick="viewGroup(${row.Group_id})">
                            <header class="group-header">
                                <div class="profile">
                                    <i class="fas fa fa-user-circle"></i>
                                </div>
                            </header>
                            <div class="group-body">
                                <blockquote class="blockquote">
                                    <h3>${row.Name}</h3>
                                    <p class="mb-0">${row.Enroll_Date}</p>
                                    <footer class="blockquote-footer"><cite title="Source Title">${row.admin}</cite></footer>
                                </blockquote>
                            </div>
                        </div>
                 
           </div>`		
	}
	
});


var btn_create = document.querySelector('#btn-create').addEventListener('click',()=>{
	return new Promise((resolve,reject)=>{
		 var user_id = localStorage.getItem('user_id')
		 var Name = document.querySelector('#GroupName').value
		var user = simpleQueryString.stringify({
			 user_id: user_id,
			 Name:Name
		});

				const config = {
				  headers: {
				    'Content-Type': 'application/x-www-form-urlencoded'
				  }
				}
		 axios.post('NewGroup',user,config).then(response=>{
			 if(response.data.Group_id){
				 toggleForm()
			 }
		 })
			
		})
})
const toggleForm = ()=>{
	 getGroups() 
	const form = document.querySelector('.card.create-group');
	if(form.style.display=="block"){
		form.style.display = "none"
		form.style.position = "static"	
	}else{
		
		form.style.display = "block"
			form.style.position = "absolute"
				form.style.left="40%"	
					form.style.zIndex="99"	
	}		
}

const viewGroup = group_id =>{
	sessionStorage.setItem('group_id',group_id)
	routers.push('ViewGroup')
}