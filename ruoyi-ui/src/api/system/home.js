import request from "@/utils/request";

export function getLineData() {
  return request({
    url: "/home/lineYoidDate",
    method: "get",
  });
}
