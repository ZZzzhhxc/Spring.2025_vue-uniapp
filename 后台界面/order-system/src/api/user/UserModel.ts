//定义列表查询的数据类型
//type：通常用于定义数据类型
export type ListUserParm={
    currentPage:number;
    pageSize:number;
    name:string;
    phone:string;
}
//表单的数据类型
export type UserModel = {
    userId:string;
    username:string;
    password:string;
    phone:string;
    email:string;
    sex:string;
    name:string;
}