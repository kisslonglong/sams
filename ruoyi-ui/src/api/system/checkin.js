import request from '@/utils/request'

// 查询员工打卡记录列表
export function listCheckin(query) {
  return request({
    url: '/system/checkin/list',
    method: 'get',
    params: query
  })
}

// 查询员工打卡记录详细
export function getCheckin(id) {
  return request({
    url: '/system/checkin/' + id,
    method: 'get'
  })
}

// 新增员工打卡记录
export function addCheckin(data) {
  return request({
    url: '/system/checkin',
    method: 'post',
    data: data
  })
}

// 修改员工打卡记录
export function updateCheckin(data) {
  return request({
    url: '/system/checkin',
    method: 'put',
    data: data
  })
}

// 删除员工打卡记录
export function delCheckin(id) {
  return request({
    url: '/system/checkin/' + id,
    method: 'delete'
  })
}
