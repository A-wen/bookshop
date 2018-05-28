function init(){
	var upfile = document.getElementsByClassName('upfile');
	
	for(var i in upfile){
		upfile[i].onchange=previewImage;
	}
}
function previewImage(){
	//取到files物件
	var file = this.files[0];
	var target = this;
	//files物件可以得到上傳檔案type
	var type = file.type.split('/')[0];
	if(type!='image'){
		alert('只提供上傳圖檔');
		this.focus();
	}else{
		//可以透過FileReader物件取到上傳檔案路徑
		var fileReader = new FileReader();
		fileReader.readAsDataURL(file);
		fileReader.onload = function(){
			//動態新增img標籤
			var image = document.createElement('img');
			image.src=fileReader.result;
			image.height=100;
			//如果div父標籤有超過3個小孩，則刪掉第一個小孩
			while(target.parentNode.childNodes.length>=3){
				target.parentNode.removeChild(target.parentNode.firstChild);
			}
			//將img標籤放到div標籤內
			target.parentNode.insertBefore(image,target);

		}
	}
	
}
window.addEventListener('load',init,false);