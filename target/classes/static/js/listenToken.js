function listenToken(){
    let that = this;
    window.addEventListener("click", e => {
        let tokenTime = window.localStorage.getItem("tokenTime");
        let now = new Date().getTime();
        if(tokenTime){
            // console.log(tokenTime)
            let timeDifference = (now - Number(tokenTime));
            // console.log(timeDifference);
            if(timeDifference > 1000 * 60 * 1){
                that.$message("重新登录");
                window.location.href="/";
                return;
            }
        }else{
            that.$message("未登录");
            window.location.href="/";
        }
    })
}