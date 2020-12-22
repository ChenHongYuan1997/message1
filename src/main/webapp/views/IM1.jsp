<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/12/5
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        /*
          //所有毒素信息
         $(function () {
            $.getJSON("/findAllSampleToxinInfo",{},function (data) {
                var toxin ="<option value=''>请选择<option/>"
                for (var i = 0; i <data.length; i++){
                    toxin += "<option value='"+data[i].id+"'>"+data[i].toxinType+"</option>";
                }
                $("#toxin_type").html(toxin);
            })

        //农作物种类

            $.getJSON("/findCropSpecies",{},function (data) {
                var crop ="<option value=''>请选择</option>"
                for (var i = 0; i <data.length; i++){
                    crop += "<option value='"+data[i].id+"'>"+data[i].cropSpecies+"</option>";
                }
                $("#crop_species").html(crop);
            })

        //所有的省信息

            $.getJSON("/findAddressProvince",{},function (data) {
                var province ="<option value=''>请选择</option>"
                for (var i=0; i<data.length; i++){
                    province += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#shen").html(province)
            })


        $("#shen").change(function () {
            //查询省选中之后多对应的市对信息
            var code = $("#shen").val();
            $.getJSON("/findAddressCity",{"code":code},function (data) {
                var city = "<option value=''>请选择</option>"
                for (var i=0; i<data.length; i++){
                    city += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#shi").html(city)
                $("#xian").html("<option value=''>请选择</option>");
            })
        })
        $("#shi").change(function () {
            //查询市选中之后所对应的县的信息
            var code = $("#shi").val();
            $.getJSON("/findAddressTown",{"code":code},function (data) {
                var town = "<option value=''>请选择</option>"
                for (var i=0; i<data.length; i++){
                    town += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#xian").html(town);
            })
        })

        })*/

        $(function () {
            /* $.ajax({
                 url:"",
                 data:{

                 },
                 type:"",
                 dataType:"",
                 success :function (data) {

                 }
             })*/

            //所有毒素信息
            $.getJSON("/findAllSampleToxinInfo",{},function (data) {

                var toxin = "<option value=''>请选择</option>"
                for (var i = 0; i <data.length ; i++) {

                        toxin += "<option  value='"+data[i].id+"'>"+data[i].toxinType+"</option>";

                        toxin += "<option value='"+data[i].id+"'>"+data[i].toxinType+"</option>";
                    }
                $("#toxin_type").html(toxin);
            })

            //农作物种类
            $.getJSON("/findCropSpecies",{},function (data) {
                var crop = "<option value=''>请选择</option>"
                for (var i = 0; i <data.length ; i++) {

                        crop += "<option value='"+data[i].id+"'>"+data[i].cropSpecies+"</option>";
                }
                $("#crop_species").html(crop);
            })

            //所有的省信息
            $.getJSON("/findAddressProvince",{},function (data) {
                var province = "<option value=''>请选择</option>"
                for (var i = 0; i <data.length ; i++) {
                        province += "<option  value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#shen").html(province);
            })

        })

        function changeCity() {
            //查询省选中之后所对应得市得信息
            var code =  $("#shen").val();
            $.getJSON("/findAddressCity",{"code":code},function (data) {
                var city = "<option value=''>请选择</option>"
                for (var i = 0; i <data.length ; i++) {
                        city += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#shi").html(city);
                $("#xian").html("<option value=''>请选择</option>");
            })
        }
        function changeCounty() {
            //查询省选中之后所对应得市得信息
            var code =  $("#shi").val();
            $.getJSON("/findAddressTown",{"code":code},function (data) {
                var town = "<option value=''>请选择</option>"
                for (var i = 0; i <data.length ; i++) {
                        town += "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
                }
                $("#xian").html(town);
            })
        }
        function realList(pageNum) {

            $("#pageNum").val(pageNum);
            $("#form1").submit();
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
                <form action="/findAll" method="get">
                    <ul>
                    <li class="bh">
                        <lable>样品编号</lable>
                        <input type="hidden" name="pageNum" id="pageNum"/>
                        <input maxlength="15" onkeyup="this.value=this.value.replace(/\s+/g,'')" id="sample_id" value="" type="text">
                    </li>
                    <li class="wrl mright">
                        <lable>污染率</lable>
                        <select id="wuranluv">
                            <option value="">请选择</option>
                            <option <c:if test="${wuranluv == '0-20'}">selected</c:if>value="0-20">0~20%</option>
                            <option <c:if test="${wuranluv == '20-40'}">selected</c:if> value="20-40">20%~40%</option>
                            <option <c:if test="${wuranluv == '40-60'}">selected</c:if>value="40-60">40%~60%</option>
                            <option <c:if test="${wuranluv == '60-80'}">selected</c:if>value="60-80">60%~80%</option>
                            <option <c:if test="${wuranluv == '80-100'}">selected</c:if>value="80-100">80%~100%</option>
                        </select>
                    </li>
                    <li class="time">
                        <lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间</lable>
                        <input id="qaz" value="${sampInfoVo.timex}" type="hidden">
                        <select name="year" id="year" class="year">
                            <option value="0">请选择</option>

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

                        </select>
                        <span> &nbsp;年</span>
                        <select name="month" id="month" class="month">
                            <option value="0">请选择</option>
                        </select>
                        <span> &nbsp;月 </span>
                        <select name="day" id="day" class="day">
                            <option value="0">请选择</option>
                        </select>
                        <span>&nbsp; 日</span> </li>
                    <li class="w mright yu">
                        <lable>主要污染菌种类</lable>
                        <input type="hidden" id="sampleToxinType" value="sampleInfoVo.toxinType"/>
                        <select name="toxin_type" class="mainSpecies" id="toxin_type">
                            <option value="">请选择</option>

                        </select>
                    </li>
                    <li class="w mright yu">
                        <lable>农作物种类</lable>
                        <input type="hidden" id="cropSpeciesId" value="${sampInfoVo.cropSpeciesId}"/>
                        <select name="crop_species" class="nzw_spacies" id="crop_species">
                            <option value="">请选择</option>

                        </select>
                    </li>
                    <li class="place w">
                        <lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地点</lable>
                        <input type="hidden" value="${sampleInfoVo.province}" id="shengs">
                        <input type="hidden" value="${sampleInfoVo.city}" id="shisss">
                        <input type="hidden" value="${sampleInfoVo.county}" id="xiansss">
                        <input type="hidden" value="123" id="wsx">
                        <select name="province" class="province"  onchange="changeCity()" id="shen" >
                            <option value="">请选择</option>

                        </select>
                        <span> &nbsp;省</span>
                        <select name="city" class="city"  onchange="changeCounty()" id="shi">
                            <option value="0">请选择</option>

                        </select>
                        <span> &nbsp;市</span>
                        <select name="county" class="county" id="xian">
                            <option value="0">请选择</option>

                        </select>
                        <span> &nbsp;县</span> </li>
                 </ul>
                <div class="soso">
                    <button type="submit">搜索</button>
                </div>
                </form>
                <div class="soso"><a href="javascript:if(confirm('确实要搜索吗?'))location='/glory/rest/iddeleteyangpin?id=754'">搜索</a></div>
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
                            <input type="file" name="file" class="file-info-b junzhu-a" accept=".xlsx">
                            <a class="xz-btn-b junzhu-b">选择文件</a>
                            <input type="text" name="" id="flag" value="" class="vall vall-c" readonly>
                            <input class="submit-b submit-c" type="button" value="导入菌株信息">
                        </form>
                        <a href="javascript:void(0)" class="dc">信息导出</a> </li>
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
                <tbody>
                <c:forEach items="${pageInfo.list}" var="samp">
                    <tr>
                        <td><input class="testyangpin" value="${samp.id}" type="checkbox"></td>
                        <td class="ybbh">${samp.sampleId}</td>
                        <td>${samp.addressProvince.name}--${samp.addressCity.name}--${samp.addressTown.name}</td>
                        <td>${samp.cropSpecies.cropSpecies}</td>
                        <td>${samp.samplingTime}</td>
                        <td><fmt:formatDate value="${samp.harvestTime}" pattern="yyyy-MM-dd"/> </td>
                        <td>${samp.pollutionRate}%</td>
                        <td>${samp.sampleToxinInfo.toxinType}</td>
                        <td><a href="editIM.html">编辑</a> |<a href="javascript:if(confirm('确实要删除吗?'))location='/glory/rest/iddeleteyangpin?id=754'">删除</a></td>
                    </tr>
                </c:forEach>
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
    <ul>
        <li class="im-indexpage"><a href="/findAll?pageNum=1">首页</a></li>
        <li class="im-nextpage"><a href="/findAll?pageNum=${pageInfo.prePage}">上一页</a></li>

        <li class="im-nextpage"><a href="/findAll?pageNum=${pageInfo.nextPage}">下一页</a></li>
        <li class="im-indexpage"><a href="/findAll?pageNum=${pageInfo.pages}">尾页</a></li>
    </ul>
</div>
<script type="text/javascript" src="../static/js/jquery1.11.3-jquery.min.js" ></script>
<script type="text/javascript" src="../static/js/browsing.js" ></script>
<script type="text/javascript" src="../static/js/linkage4.js"></script>
</body>
</html>

