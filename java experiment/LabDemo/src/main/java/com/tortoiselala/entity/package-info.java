package com.tortoiselala.entity;

/**
 * 这个包中包含了数据库持久化操作的需要的一些对象单元
 * 分别对应着各个表的行对象
 * - T_KSXX(科室信息表)DepartmentInformationTableUnit
 *      - KSBH(科室编号)DepartmentID
 *      - KSMC(科室名称)DepartmentName
 *      - PYZS(科室名称的拼音字首)DepartmentPinyinPrefix
 *
 *
 * - T_BRXX(病人信息表)PatientInformationTableUnit
 *      - BRBH(病人编号)PatientID
 *      - BRMC(病人名称)PatientName
 *      - DLKL(登录口令)PatientPassword
 *      - YCJE(病人预存金额)PatientBalence
 *      - DLRQ(最后一次登录日期以及时间)PatientLastLoginDate
 *
 *
 * - T_KSYS(科室医生表)DepartmentDoctorTableUnit
 *      - YSBH(医生编号)DoctorID
 *      - KSBH(科室编号)DepartmentID
 *      - YSMC(医生名称)DoctorName
 *      - PYZS(医生名称的拼音字首)DoctorPinyinPrefix
 *      - DLKL(登录口令)DoctorPassword
 *      - SFZJ(是否专家)DoctorType
 *      - DLRQ(最后一次登录日期及时间)DoctorLastLoginDate
 *
 *
 * - T_HZXX(号种信息表)NumberInformationTableUnit
 *      - HZBH(号种编号)QueueTypeID
 *      - HZMC(号种名称)QueueTypeName
 *      - PYZS(号种名称的拼音字首)QueueTypePinyinPrifix
 *      - KSBH(号种所属科室)QueueTypeDepartmentID
 *      - SFZJ(是否专家号)QueueType
 *      - GHRS(每日限定的挂号人数)MaxNumberOfPeople
 *      - GHFY(挂号费)RegistrationFee
 *
 *
 * - T_GHXX(挂号信息表)RegistrationInformationTableUnit
 *      - GHBH(挂号的顺序编号，数字)QueueID
 *      - HZBH(号种编号)QueueTypeID
 *      - YSBH(医生编号)DoctorID
 *      - BRBH(病人编号)PatientID
 *      - GHRC(该病人该号种的挂号人次)NumberOfRegisteredPerson
 *      - THBZ(退号标志=true为已退号码)CancelingFlag
 *      - GHFY(病人的实际挂号费用)QueueFee
 *      - RQSJ(挂号日期时间)QueueDate
 */