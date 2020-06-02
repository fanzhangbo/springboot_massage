// pages/morder/morder.js
var app = getApp();
var host = app.globalData.host;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    host:host,
    massage_list: []
  },

  show_detail: function(e) {
    console.log(e);
    let mid = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/massage_detail/massage_detail?id=' + mid,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(!wx.getStorageSync('user')) {
      wx.reLaunch({
        url: '/pages/login/login',
      })
    }
    let that = this;
    wx.request({
      url: host + '/api/massageOrder/listMassageOrder', //仅为示例，并非真实的接口地址
      data: {
        userId: wx.getStorageSync('user').id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log(res.data)
        that.setData({
          massage_list:res.data.data
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that = this;
    wx.request({
      url: host + '/api/massageOrder/listMassageOrder', //仅为示例，并非真实的接口地址
      data: {
        userId: wx.getStorageSync('user').id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log(res.data)
        that.setData({
          massage_list:res.data.data
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})