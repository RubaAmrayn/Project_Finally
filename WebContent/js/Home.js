const getCodes = ()=>{
	return new Promise((resolve,reject)=>{
	 var user_id = localStorage.getItem('user_id');//store in Permanent memory 
	var user = simpleQueryString.stringify({//Library convert object to "url encode"
		  'user_id': user_id
	});

			const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	 axios.post('Home',user,config).then(response=>{ //send data to server
		 resolve(response)
	 })
		
	})
}
getCodes().then(res=>{//Add content
	let tbody = document.querySelector('tbody#table-data')
	let table_row = ''
	for(let row of res.data.codes){
		table_row+=`
		 <tr class="table-light">
            <th scope="row">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="${row.Code_id}">
                    <label class="custom-control-label" for="${row.Code_id}">Share</label>
                </div>
            </th>
            <td class="Code-Name" data-to="${row.code_id}">${row.Title}</td>
            <td>${row.Create_Date}</td>
            <td>${row.Language}</td>
            <td>${row.permition}</td>
            <td><a onclick="routers.push('viewCode',${row.Code_id})" class="viewCode"><button class="btn btn-block btn-light">View</button></a></td>
        </tr>`		
	}
	tbody.innerHTML = table_row
});
