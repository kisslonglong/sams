import request from '@/utils/request'

// 查询员工打卡记录列表
export function listCheck(query) {
  return request({
    url: '/system/checkin/list',
    method: 'get',
    params: query
  })
}

// 查询员工打卡记录详细
export function getCheck(id) {
  return request({
    url: '/system/checkin/' + id,
    method: 'get'
  })
}

// 新增员工打卡记录
export function addCheck(data) {
  return request({
    url: '/system/checkin',
    method: 'post',
    data: data
  })
}

// 修改员工打卡记录
export function updateCheck(data) {
  return request({
    url: '/system/checkin',
    method: 'put',
    data: data
  })
}

// 删除员工打卡记录
export function delCheck(id) {
  return request({
    url: '/system/checkin/' + id,
    method: 'delete'
  })
}


export function userCheckIn(params){
  return request({
    url: '/UserCheck/checkIn',
    method: 'get',
    params: params
  })
}

export function leavere(params){
  return request({
    url: '/UserCheck/askLever',
    method: 'get',
    params: params
  })
}