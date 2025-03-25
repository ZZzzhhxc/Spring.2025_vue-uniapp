import http from "@/http";
import type { UserModel } from "./UserModel";
//新增
export const addUserApi= (parm:UserModel)=>{
    return http.post("/api/user",parm)
}