import { ElNotification } from "element-plus";

export const onError = async (msg: string, error: any) => {
  ElNotification({
    title: "失败",
    message: `${msg} - ${error}`,
    type: "error",
  });
};

export const onSuccess = async (msg: string, success: any) => {
  ElNotification({
    title: "成功",
    message: `${msg} - ${success}`,
    type: "success",
  });
};
