import request from '@/utils/request'

export function staffList(){
  return request({
    url: '/system/zg_information/staffList',
    method: 'get'
  })
}


// 查询职工信息列表
export function listZg_information(query) {
  return request({
    url: '/system/zg_information/list',
    method: 'get',
    params: query
  })
}

// 查询职工信息详细
export function getZg_information(id) {
  return request({
    url: '/system/zg_information/' + id,
    method: 'get'
  })
}

// 新增职工信息
export function addZg_information(data) {
  return request({
    url: '/system/zg_information',
    method: 'post',
    data: data
  })
}

// 修改职工信息
export function updateZg_information(data) {
  return request({
    url: '/system/zg_information',
    method: 'put',
    data: data
  })
}

// 删除职工信息
export function delZg_information(id) {
  return request({
    url: '/system/zg_information/' + id,
    method: 'delete'
  })
}
export function importDate(data){
  return({
    url:"/system/zg_information/import",
    method:"post",
    data
  })
}