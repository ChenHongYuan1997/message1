angular.module("IMService",[]).service("$Service",function($http){
    this.find = function(url,data,method){//路径  参数  get/post
        if ("get" == method){
            return $http({
                url:url,
                method:method,
                params:data
            });
        }else {
            return $http({
                url:url,
                method:method,
                data:data,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                transformRequest: function(obj) {
                    var str = [];

                    for (var s in obj) {
                        str.push(encodeURIComponent(s) + "=" + encodeURIComponent(obj[s]));
                    }
                    return str.join("&");
                }
            });
        }
    }
});