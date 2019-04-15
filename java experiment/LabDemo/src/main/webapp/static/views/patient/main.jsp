<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tortoiselala
  Date: 2019-03-27
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <style>
        th, tr, td, table {
            border: 1px solid red;
        }
    </style>
</head>
<body>

<div style="text-align: center;">
    <form class="form-group" action="<c:url value="/registration.do"/>">

        <div class="form-line-1">
            <label>科室名称：</label>
            <select class="form-department-name" id="departmentName" name="departmentName"
                    onchange="changeDoctorNameWhileDepartmentNameChanged(this.value)">
                <option value="">-- 科室名称 --</option>
            </select>

            <label>医生名称：</label>
            <select class="form-doctor-name" id="doctorName" name="doctorName"
                    onchange="changeQueueTypeWhileDoctorChanged(this.value)">
                <option value="">-- 医生名称 --</option>
            </select>
        </div>

        <div class="form-line-2">
            <label>号种类别：</label>
            <select class="form-queue-type" id="queueType" name="queueType" onchange="">
                <option value="">-- 号别种类 --</option>
            </select>
            <label>号种名称：</label>
            <select class="form-queue-name" id="queueName" name="queueName" onchange="changeFeeWhileQueueTypeChanged">
                <option>-- 号种名称 --</option>
            </select>
        </div>

        <div class="form-line-3">
            <label>交款金额：</label>
            <input class="input-charge" id="inputCharge" name="inputCharge"/>
            <label>应缴金额：</label>
            <input class="input-amount-due" id="amountDue" name="amountDue"/>
        </div>

        <div class="form-line-4">
            <label>找零金额：</label>
            <input class="input-amount-charge" id="amountCharge" name="amountCharge"/>
            <label>挂号号码：</label>
            <input class="input-queue-number" id="queueNumber" name="queueName"/>
        </div>

        <button  onclick="submitFormWhileSubmitButtonClicked">提交</button>
    </form>
</div>

</table>
<script type="text/javascript">

    var departmentList = ${DEPARTMENT_LIST};
    var departmentSelect = document.getElementById('departmentName');
    for (var i = 0; i < departmentList.length; ++i) {
        var opEle = document.createElement("option");
        opEle.value = departmentList[i].departmentID;
        opEle.appendChild(document.createTextNode(departmentList[i].departmentName));
        departmentSelect.appendChild(opEle);
    }
    // 设置交款金额与找零金额不可操作
    if(${IS_CHARGE_ENOUGH}){
        document.getElementById('inputCharge').disabled = true;
        document.getElementById('amountCharge').disabled = true;
    }


    function changeDoctorNameWhileDepartmentNameChanged(val) {
        var departmentID = "";
        var departmentArray = ${DEPARTMENT_LIST};



        for (var i = 0; i < departmentArray.length; ++i) {
            if (val == departmentArray[i].departmentID) {
                departmentID = departmentArray[i].departmentID;
                break;
            }
        }
        console.log("departmentID:" + departmentID)
        console.log("val:" + val);

        var doctorName = document.getElementById("doctorName");
        doctorName.length = 0;
        var doctorArray = ${DOCTOR_LIST};


        for (var i = 0; i < doctorArray.length; ++i) {
            if (doctorArray[i].departmentID === departmentID) {
                var textNode = document.createTextNode(doctorArray[i].doctorName);
                var opEle = document.createElement("option");

                opEle.appendChild(textNode);
                opEle.value = doctorArray[i].doctorID;
                doctorName.appendChild(opEle);
            }
        }

        // 更新号种信息
        var doctorName = document.getElementById("queueName");
        doctorName.length = 0;
        var doctorArray = ${REG_INFO_LIST};


        for (var i = 0; i < doctorArray.length; ++i) {
            if (doctorArray[i].ID === departmentID) {
                var textNode = document.createTextNode(doctorArray[i].name);
                var opEle = document.createElement("option");
                opEle.value = doctorArray[i].ID;
                opEle.appendChild(textNode);
                doctorName.appendChild(opEle);
            }
        }
    }
    
    function changeQueueTypeWhileDoctorChanged(val) {
        var doctorArray = ${DOCTOR_LIST};
        var queueType = document.getElementById("queueType");
        queueType.length = 0;
        document.getElementById('amountDue').value = 20;
        for (var i = 0; i < doctorArray.length; ++i) {
            if (doctorArray[i].doctorID === val) {

                if(doctorArray[i].expert === true){

                    var opEleExpert = document.createElement("option");
                    opEleExpert.appendChild(document.createTextNode("专家号"));
                    opEleExpert.value = "1";
                    var opEleNormal = document.createElement("option");
                    opEleNormal.appendChild(document.createTextNode("普通号"));
                    opEleExpert.value = "0";
                    queueType.appendChild(opEleExpert);
                    queueType.appendChild(opEleNormal);
                }else{
                    var textNode = document.createTextNode("普通号");
                    var opEle = document.createElement("option");
                    opEle.value = "0";
                    opEle.appendChild(textNode);
                    queueType.appendChild(opEle);
                }
               break;
            }
        }
    }

    function changeFeeWhileQueueTypeChanged(val) {
        document.getElementById('amountDue').valueOf = 20;
    }
    
    function submitFormWhileSubmitButtonClicked() {
        window.alert('已经成功提交表单，正在排号');
        var temp = document.createElement('form');
        temp.action = "registration.do";
        temp.method = "post";
        temp.style.display = 'none';

        var departmentName = document.getElementById("departmentName").valueOf();
        var doctorName = document.getElementById("doctorName").valueOf();
        var registrationType = document.getElementById("queueType").valueOf();
        var registrationName = document.getElementById("queueName").valueOf();
        var inputCharge = document.getElementById("inputCharge").valueOf();
        var amountDue = document.getElementById("amountDue").valueOf();
        var amountCharge = document.getElementById("amountCharge").valueOf();

        console.log("ready to submit"
                    + "\ndepartmentID:" + departmentName
            + "\ndoctorName:" + doctorName
            + "\nregistrationName:" + registrationName
            + "\nregistrationType:" + registrationType
            + "\ninputCharge:" + inputCharge
            + "\namountDue:" + amountDue
            + "\namountCharge:" + amountCharge
        );

        document.body.appendChild(temp);
        temp.submit();
    }
</script>
</body>
</html>
