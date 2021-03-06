<%--
  Created by IntelliJ IDEA.
  User: GL62
  Date: 2020/9/5
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <base>
    <title>信息管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>信息管理</title>
    <link rel="stylesheet" type="text/css" href="../static/css/reset.css"/>
    <script src="../static/js/jquery.js"></script>
    <script src="../static/js/IM-index.js"></script>
    <link rel="stylesheet" type="text/css" href="../static/css/IM-index.css"/>
    <script src="../static/js/super.js"></script>
    <style>
        html, body {
            height: 100%;
        }
    </style>

    <script type="text/javascript">


        function realList(pageNum) {
//表单序列化
            var formDate = $("#form1").serialize();

            if (pageNum == null || pageNum == ""){
                pageNum = 1;
            }
            formDate += "&pageNum="+pageNum;
            //查询全部信息
            $.ajax({
                url:"/findAll",
                data:formDate,
                type:"get",
                dataType:"json",
                success:function(data){
                    $("#tbody").empty();
                    $("#pageIm").empty();
                    var list = data.result.list;
                    var sampleinfo = '';
                    for (var i = 0; i < list.length; i++) {
                        var samp = list[i];
                        sampleinfo += ' <tr>\n' +
                            '                    <td><input class="testyangpin" value="'+samp.id+'" type="checkbox"></td>\n' +
                            '                    <td class="ybbh">'+samp.sampleId+'</td>\n' +
                            '                    <td>'+samp.addressProvince.name+' -- '+samp.addressCity.name+'--'+samp.addressTown.name+'</td>\n' +
                            '                    <td>'+samp.cropSpecies.cropSpecies+'</td>\n' +
                            '                    <td>'+samp.harvestTime+'</td>\n' +
                            '                    <td>'+samp.harvestTime+'</td>\n' +
                            '                    <td>'+samp.pollutionRate+'</td>\n' +
                            '                    <td>'+samp.sampleToxinInfo.toxinType+'</td>\n' +
                            '                    <td><a href="editIM.html?id='+samp.id+'">编辑</a> |<a >删除</a></td>\n' +
                            '                </tr>';
                    }
                    //$("#tbody").html(sampleinfo);
                    $("#tbody").append(sampleinfo);
                    var pageUtil = data.result;
                    var page = '<li class="im-indexpage"><a onclick="realList(1)" >首页</a></li>\n' +
                        '        <li class="im-nextpage"><a  onclick="realList('+pageUtil.prePage+')">上一页</a></li>\n' +
                        '        <li class="im-nextpage"><a onclick="realList('+pageUtil.nextPage+')" >下一页</a></li>\n' +
                        '        <li class="im-indexpage"><a  onclick="realList('+pageUtil.pages+')">尾页</a></li>'
                    //$("#pageIm").html(page)
                    $("#pageIm").append(page);

                }
            })

        }
        $(function () {

            //调取方法进行查询全部信息
            realList();
            //查询毒素信息
            $.getJSON("/findAllSampleToxinInfo",{},function (data) {

                $("#toxin_type").empty();
                var toxin = '<option value="">请选择</option>';
                for (var i = 0; i <data.length; i ++){

                    toxin += '<option value="'+data[i].id+'">'+data[i].toxinType+'</option>'


                }
                $("#toxin_type").append(toxin);

            })
            //查询品种信息
            $.getJSON("/findAllCropSpecies",{},function (data) {
                $("#crop_species").empty();
                var species = '<option value="">请选择</option>';
                for (var i = 0; i <data.length; i ++){
                    species += '<option value="'+data[i].id+'">'+data[i].cropSpecies+'</option>'
                }
                $("#crop_species").append(species);
            })
            /*查询省的信息*/
            $.getJSON("/findAddressProvince",{},function (data) {
                $("#shen").empty();

                var province = '<option value="">请选择</option>';

                for (var i = 0; i <data.length; i ++){
                    province += '<option value="'+data[i].code+'">'+data[i].name+'</option>'



                }
                $("#shen").append(province);
            })


        })
        function  changeCity() {
            var code =  $("#shen").val();
            $.getJSON("/findAddressCity",{"code":code},function (data) {
                $("#shi").empty();
                var city = '<option value="">请选择</option>';
                for (var i = 0; i <data.length; i ++){
                    city += '<option value="'+data[i].code+'">'+data[i].name+'</option>'



                }
                $("#shi").append(city);
            })
        }

        function changeCounty() {
            var code =  $("#shi").val();
            $.getJSON("/findAddressTown",{"code":code},function (data) {
                $("#xian").empty();

                var county = '<option value="">请选择</option>';
                for (var i = 0; i <data.length; i ++){
                    county += '<option value="'+data[i].code+'">'+data[i].name+'</option>'

                }
                $("#xian").append(county);
            })
        }
        function daochu() {
            location.href="/exportExcel";

        }

        function daoru() {
            var formdata=new FormData();
            $.each($("input[name=filexin]")[0].files,function (index,item) {
                formdata.append("file",item);
            })
            console.log(formdata);
            $.ajax({

                url: "/toLead" ,

                type: "POST",

                data: formdata,

                async: false,

                contentType: false,

                processData: false,

                success: function (result) {
                    console.log(result);
                    alert(result);
                }
            });
        }
    </script>
</head>

<body>
<a name="top"></a>
<div class="contaner">
    <div class="im-content">
        <div class="im-heade">
            <div class="imhead-title">当前页面：<a>信息管理</a></div>
            <div class="imhead-select">
                <form action="/findAll" method="post" id="form1">
                    <ul>
                        <li class="bh">
                            <lable>样品编号</lable>
                            <input maxlength="15" name="sampleId"  onkeyup="this.value=this.value.replace(/\s+/g,'')" id="sample_id" value="${dto.sampleId}" type="text">
                        </li>
                        <li class="wrl mright">
                            <lable>污染率</lable>
                            <select id="wuranluv" name="wuranluv">
                                <option value="">请选择</option>


                                <option value="0-20">0~20%</option>
                                <option value="20-40">20%~40%</option>
                                <option value="40-60">40%~60%</option>
                                <option value="60-80" >60%~80%</option>
                                <option value="80-100" >80%~100%</option>
                            </select>
                        </li>
                        <li class="time">
                            <lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间</lable>
                            <input id="qaz" type="hidden">
                            <select name="year" id="year" class="year">
                                <option value="">请选择</option>
                                <option value="1950">1950</option>
                                <option value="1951">1951</option>
                                <option value="1952">1952</option>
                                <option value="1953">1953</option>
                                <option value="1954">1954</option>
                                <option value="1955">1955</option>
                                <option value="1956">1956</option>
                                <option value="1957">1957</option>
                                <option value="1958">1958</option>
                                <option value="1959">1959</option>
                                <option value="1960">1960</option>
                                <option value="1961">1961</option>
                                <option value="1962">1962</option>
                                <option value="1963">1963</option>
                                <option value="1964">1964</option>
                                <option value="1965">1965</option>
                                <option value="1966">1966</option>
                                <option value="1967">1967</option>
                                <option value="1968">1968</option>
                                <option value="1969">1969</option>
                                <option value="1970">1970</option>
                                <option value="1971">1971</option>
                                <option value="1972">1972</option>
                                <option value="1973">1973</option>
                                <option value="1974">1974</option>
                                <option value="1975">1975</option>
                                <option value="1976">1976</option>
                                <option value="1977">1977</option>
                                <option value="1978">1978</option>
                                <option value="1979">1979</option>
                                <option value="1980">1980</option>
                                <option value="1981">1981</option>
                                <option value="1982">1982</option>
                                <option value="1983">1983</option>
                                <option value="1984">1984</option>
                                <option value="1985">1985</option>
                                <option value="1986">1986</option>
                                <option value="1987">1987</option>
                                <option value="1988">1988</option>
                                <option value="1989">1989</option>
                                <option value="1990">1990</option>
                                <option value="1991">1991</option>
                                <option value="1992">1992</option>
                                <option value="1993">1993</option>
                                <option value="1994">1994</option>
                                <option value="1995">1995</option>
                                <option value="1996">1996</option>
                                <option value="1997">1997</option>
                                <option value="1998">1998</option>
                                <option value="1999">1999</option>
                                <option value="2000">2000</option>
                                <option value="2001">2001</option>
                                <option value="2002">2002</option>
                                <option value="2003">2003</option>
                                <option value="2004">2004</option>
                                <option value="2005">2005</option>
                                <option value="2006">2006</option>
                                <option value="2007">2007</option>
                                <option value="2008">2008</option>
                                <option value="2009">2009</option>
                                <option value="2010">2010</option>
                                <option value="2011">2011</option>
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                                <option value="2015">2015</option>
                                <option value="2016">2016</option>
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                                <option value="2022">2022</option>
                                <option value="2023">2023</option>
                                <option value="2024">2024</option>
                                <option value="2025">2025</option>
                                <option value="2026">2026</option>
                                <option value="2027">2027</option>
                                <option value="2028">2028</option>
                                <option value="2029">2029</option>
                                <option value="2030">2030</option>
                                <option value="2031">2031</option>
                                <option value="2032">2032</option>
                                <option value="2033">2033</option>
                                <option value="2034">2034</option>
                                <option value="2035">2035</option>
                                <option value="2036">2036</option>
                                <option value="2037">2037</option>
                                <option value="2038">2038</option>
                                <option value="2039">2039</option>
                                <option value="2040">2040</option>
                                <option value="2041">2041</option>
                                <option value="2042">2042</option>
                                <option value="2043">2043</option>
                                <option value="2044">2044</option>
                                <option value="2045">2045</option>
                                <option value="2046">2046</option>
                                <option value="2047">2047</option>
                                <option value="2048">2048</option>
                                <option value="2049">2049</option>
                                <option value="2050">2050</option>
                                <option value="2051">2051</option>
                                <option value="2052">2052</option>
                                <option value="2053">2053</option>
                                <option value="2054">2054</option>
                                <option value="2055">2055</option>
                                <option value="2056">2056</option>
                                <option value="2057">2057</option>
                                <option value="2058">2058</option>
                                <option value="2059">2059</option>
                                <option value="2060">2060</option>
                                <option value="2061">2061</option>
                                <option value="2062">2062</option>
                                <option value="2063">2063</option>
                                <option value="2064">2064</option>
                                <option value="2065">2065</option>
                                <option value="2066">2066</option>
                                <option value="2067">2067</option>
                                <option value="2068">2068</option>
                                <option value="2069">2069</option>
                                <option value="2070">2070</option>
                                <option value="2071">2071</option>
                                <option value="2072">2072</option>
                                <option value="2073">2073</option>
                                <option value="2074">2074</option>
                                <option value="2075">2075</option>
                                <option value="2076">2076</option>
                                <option value="2077">2077</option>
                                <option value="2078">2078</option>
                                <option value="2079">2079</option>
                                <option value="2080">2080</option>
                                <option value="2081">2081</option>
                                <option value="2082">2082</option>
                                <option value="2083">2083</option>
                                <option value="2084">2084</option>
                                <option value="2085">2085</option>
                                <option value="2086">2086</option>
                                <option value="2087">2087</option>
                                <option value="2088">2088</option>
                                <option value="2089">2089</option>
                                <option value="2090">2090</option>
                                <option value="2091">2091</option>
                                <option value="2092">2092</option>
                                <option value="2093">2093</option>
                                <option value="2094">2094</option>
                                <option value="2095">2095</option>
                                <option value="2096">2096</option>
                                <option value="2097">2097</option>
                                <option value="2098">2098</option>
                                <option value="2099">2099</option>
                                <option value="2100">2100</option>
                                <option value="2101">2101</option>
                                <option value="2102">2102</option>
                                <option value="2103">2103</option>
                                <option value="2104">2104</option>
                                <option value="2105">2105</option>
                                <option value="2106">2106</option>
                                <option value="2107">2107</option>
                                <option value="2108">2108</option>
                                <option value="2109">2109</option>
                                <option value="2110">2110</option>
                                <option value="2111">2111</option>
                                <option value="2112">2112</option>
                                <option value="2113">2113</option>
                                <option value="2114">2114</option>
                                <option value="2115">2115</option>
                                <option value="2116">2116</option>
                                <option value="2117">2117</option>
                                <option value="2118">2118</option>
                                <option value="2119">2119</option>
                                <option value="2120">2120</option>
                                <option value="2121">2121</option>
                                <option value="2122">2122</option>
                                <option value="2123">2123</option>
                                <option value="2124">2124</option>
                                <option value="2125">2125</option>
                                <option value="2126">2126</option>
                                <option value="2127">2127</option>
                                <option value="2128">2128</option>
                                <option value="2129">2129</option>
                                <option value="2130">2130</option>
                                <option value="2131">2131</option>
                                <option value="2132">2132</option>
                                <option value="2133">2133</option>
                                <option value="2134">2134</option>
                                <option value="2135">2135</option>
                                <option value="2136">2136</option>
                                <option value="2137">2137</option>
                                <option value="2138">2138</option>
                                <option value="2139">2139</option>
                                <option value="2140">2140</option>
                                <option value="2141">2141</option>
                                <option value="2142">2142</option>
                                <option value="2143">2143</option>
                                <option value="2144">2144</option>
                                <option value="2145">2145</option>
                                <option value="2146">2146</option>
                                <option value="2147">2147</option>
                                <option value="2148">2148</option>
                                <option value="2149">2149</option>
                                <option value="2150">2150</option>
                                <option value="2151">2151</option>
                                <option value="2152">2152</option>
                                <option value="2153">2153</option>
                                <option value="2154">2154</option>
                                <option value="2155">2155</option>
                                <option value="2156">2156</option>
                                <option value="2157">2157</option>
                                <option value="2158">2158</option>
                                <option value="2159">2159</option>
                                <option value="2160">2160</option>
                                <option value="2161">2161</option>
                                <option value="2162">2162</option>
                                <option value="2163">2163</option>
                                <option value="2164">2164</option>
                                <option value="2165">2165</option>
                                <option value="2166">2166</option>
                                <option value="2167">2167</option>
                                <option value="2168">2168</option>
                                <option value="2169">2169</option>
                                <option value="2170">2170</option>
                                <option value="2171">2171</option>
                                <option value="2172">2172</option>
                                <option value="2173">2173</option>
                                <option value="2174">2174</option>
                                <option value="2175">2175</option>
                                <option value="2176">2176</option>
                                <option value="2177">2177</option>
                                <option value="2178">2178</option>
                                <option value="2179">2179</option>
                                <option value="2180">2180</option>
                                <option value="2181">2181</option>
                                <option value="2182">2182</option>
                                <option value="2183">2183</option>
                                <option value="2184">2184</option>
                                <option value="2185">2185</option>
                                <option value="2186">2186</option>
                                <option value="2187">2187</option>
                                <option value="2188">2188</option>
                                <option value="2189">2189</option>
                                <option value="2190">2190</option>
                                <option value="2191">2191</option>
                                <option value="2192">2192</option>
                                <option value="2193">2193</option>
                                <option value="2194">2194</option>
                                <option value="2195">2195</option>
                                <option value="2196">2196</option>
                                <option value="2197">2197</option>
                                <option value="2198">2198</option>
                                <option value="2199">2199</option>
                            </select>
                            <span> &nbsp;年</span>
                            <select name="month" id="month" class="month">
                                <option value="">请选择</option>
                            </select>
                            <span> &nbsp;月 </span>
                            <select name="day" id="day" class="day">
                                <option value="">请选择</option>
                            </select>
                            <span>&nbsp; 日</span> </li>
                        <li class="w mright yu">
                            <lable>主要污染菌种类</lable>
                            <select name="toxinType" class="mainSpecies" id="toxin_type">
                                <option value="">请选择</option>
                            </select>
                        </li>
                        <li class="w mright yu">
                            <lable>农作物种类</lable>
                            <select name="cropSpecies" class="nzw_spacies" id="crop_species">
                                <option value="">请选择</option>
                            </select>
                        </li>
                        <li class="place w">
                            <lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地点</lable>
                            <select name="province" class="province" id="shen" onchange="changeCity()">
                                <option value="">请选择</option>
                            </select>
                            <span> &nbsp;省</span>
                            <select name="city" class="city" id="shi" onchange="changeCounty()">
                                <option value="">请选择</option>
                            </select>
                            <span> &nbsp;市</span>
                            <select name="county" class="county" id="xian">
                                <option value="">请选择</option>
                            </select>
                            <span> &nbsp;县</span> </li>
                    </ul>
                </form>
                <div class="soso"><a  onclick="realList()">搜索</a></div>
            </div>
            <div class="oper">
                <ul>
                    <li class="btn-top"> <a href="editIM-add.html" class="dt">新增单条</a>
                        <form class="form-b" action="rest/excel/updateExcel" method="post" enctype="multipart/form-data" id="excelForm">
                            <input type="file" name="file" class="file-info-b  file-info-b-1" accept=".xlsx">
                            <a class="xz-btn-b xz-btn-b-1">选择文件</a>
                            <input type="text" name="" id="" value="" class="vall vall-1" readonly>
                            <input class="submit-b" type="button" value="导入样品信息">
                        </form>
                        <a class="mb">下载模板</a> <br>
                        <a class="pl">批量删除</a>
                        <!-- 产毒菌株信息的导入 -->
                        <form class="form-b" action="rest/excel/updateBacterialExcel" method="post" enctype="multipart/form-data" id="excelForm1">
                            <input type="file"  name="filexin" class="file-info-b junzhu-a" accept=".xlsx">
                            <a class="xz-btn-b junzhu-b">选择文件</a>
                            <input type="text" name="" id="flag" value="" class="vall vall-c" readonly>
                            <input class="submit-b submit-c" type="button" onclick="daoru()" value="导入信息">
                        </form>
                        <a href="javascript:daochu()" class="dc">全部导出</a>
                        <a href="javascript:tiaojiandaochu()" class="dc">查询条件导出</a>
                        <a href="javascript:gouxuandaochu()" class="dc">勾选导出</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="im-table">
            <table>
                <thead>
                <tr>
                    <th class="w1"><input type="checkbox" id="checkbox1" value="">
                        全选</th>
                    <th class="w2">样品编号</th>
                    <th class="w3">地点</th>
                    <th class="w4">农产品加工类型</th>
                    <th class="w5">取样时间</th>
                    <th class="w6">录入时间</th>
                    <th class="w7">真菌污染率</th>
                    <th class="w8">主要毒素</th>
                    <th class="w9">操作</th>
                </tr>
                </thead>
                <tbody id="tbody">

                </tbody>
            </table>
        </div>
        <div class="page"></div>
    </div>
</div>
<!-- <link rel="stylesheet" type="text/css" href="../css/style.css"> -->
<link type="text/css" rel="stylesheet" href="../static/css/paging.css">
<style type="text/css">
    .color a {
        padding: 5px;
        color: #4c4743;
        /* font-size: 20px; */
    }

    .color a:hover {
        color: #ed6c44;
    }

    /* .color2 {
        float: right;
        color: #4c4743;
        font-size: 20px;
        border: 1px black hidden;
    } */

    .color {
        /* 	float: right; */
        border: 1px black hidden;
        width: 100%;
        text-align: center;
    }

    #checkOne {
        color: #000;
        font-size: 20px;
    }
    #checkOnediv{
        color: #ed6c44;

    }

    /* #h1 {
        color: #4c4743;
    } */
</style>
<div class="im-page">
    <ul id="pageIm">

    </ul>
</div>
<script type="text/javascript" src="../static/js/jquery1.11.3-jquery.min.js" ></script>
<script type="text/javascript" src="../static/js/browsing.js" ></script>
<script type="text/javascript" src="../static/js/linkage4.js"></script>
</body>
</html>

