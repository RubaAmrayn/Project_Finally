const ViewGroup = ()=>{//
	return new Promise((resolve,reject)=>{
	 var Group_id = sessionStorage.getItem('group_id')
	var user = simpleQueryString.stringify({
		 Group_id: Group_id
	});

			const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	 axios.post('ViewGroup',user,config).then(response=>{
		 resolve(response)
	 })
		
	})
}

ViewGroup().then(res=>{
	var code_box = document.querySelector('div.code-box')
	if(res.data.Codes.length == 0){
		code_box.innerHTML+='<h2>للأسف لم يتم مشاركة اي كود في هذه المجموعة</h2>'
	}else{
	for(let row of res.data.Codes){
		 code_box.innerHTML+=`
		  <div class="list-group" style="max-width: 800px; margin:20px auto;">
                <a  onclick="routers.push('SharedCode',${row.Codes_Code_id})"
                    class="list-group-item list-group-item-action flex-column align-items-start active">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">by Ruba</h5>
                        <small>${row.Share_Date}</small>
                    </div>
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">${row.Title}</h5>

                    </div>
                </a>
            </div>
		   `		
	}
	}
});
