<template>
	<view class="container">
			<view class="example">
				<uni-forms ref="baseForm" :modelValue="baseFormData"  :rules="rules">
					
					<uni-forms-item label-width="150upx" label="项目名称"  required>
						<uni-easyinput :disabled="true" v-model="projectData.projectName" :styles="styles" />
					</uni-forms-item>
					<uni-forms-item label-width="150upx" label="是否竣工" name="projectEnd">
						<view style="line-height: 64upx;">
							<radio-group v-model="baseFormData.projectEnd">
								<label style="margin-right:20upx;">
									<radio :value="true" :checked="baseFormData.projectEnd == true" />
									 &nbsp;&nbsp;是
								</label>
								<label class="radio">
									<radio :value="false" :checked="baseFormData.projectEnd == false"/>
									 &nbsp;&nbsp;否
								</label>
							</radio-group>
						</view>
					</uni-forms-item>
					<uni-forms-item label-width="150upx" label="上报日期"  required>
						<uni-easyinput :disabled="true" v-model="today" :styles="styles"/>
					</uni-forms-item>
					<uni-forms-item label-width="150upx" label="项目经理"  required>
						<uni-easyinput :disabled="true" v-model="baseFormData.Name" :styles="styles"/>		
					</uni-forms-item>
					<view class="line"></view>
					
					<uni-forms-item label-width="300upx" label="应出勤施工人数"  required>
						<uni-easyinput type="number" v-model="baseFormData.planPeopleNum" />		
					</uni-forms-item>
					<uni-forms-item label-width="300upx" label="应出勤施工人数"  required>
						<uni-easyinput type="number" v-model="baseFormData.realPeopleNum" />		
					</uni-forms-item>
					<uni-forms-item label-width="150upx" label="开工情况">
						<uni-data-select v-model="baseFormData.projectInfo" :localdata="projectList" :clear="false"></uni-data-select>
					</uni-forms-item>
					<uni-forms-item label-width="320upx" label="截止本期累计完成投资">
						<uni-easyinput v-model="baseFormData.jzbqljwctz" disabled/>		
					</uni-forms-item>
					<uni-forms-item label-width="300upx" label="当年累计完成投资">
						<uni-easyinput v-model="baseFormData.dnljwctz" disabled/>		
					</uni-forms-item>
					<uni-forms-item label-width="300upx" label="当年投资完成率%">
						<uni-easyinput v-model="baseFormData.dntzwcl" disabled/>		
					</uni-forms-item>
					<uni-forms-item label-width="300upx" label="本期完成投资(万元)" required>
						<uni-easyinput v-model="baseFormData.completionOfInvestment" />		
					</uni-forms-item>
					<!-- <view class="line"></view>
					
					<uni-forms-item style="display: flex;align-items: center;margin-left: 10upx;" label-width="500upx" label="资金填报">
						<view style="display: flex;align-items: center;color: #1E91D4;margin-left: 60upx;" @click="addMoney()">
							<uni-icons color="#1E91D4" type="plus" size="20"></uni-icons>
							<text style="text-align: center;padding-bottom: 4upx;padding-left: 15upx;">添加</text> 
						</view>
					</uni-forms-item>
					<view class="line"></view> -->
					
					<!-- <view v-for="(item,index) in baseFormData.projectImplementationFundList">
						<uni-forms-item style="display: flex;align-items: center;margin-left: 10upx;" label-width="500upx" :label="'资金上报'+(index+1)">
							<view style="display: flex;align-items: center;color: #FF4C46;margin-left: 60upx;" @click="delMoney(index)">
								<uni-icons color="#FF4C46" type="close" size="20"></uni-icons>
								<text style="text-align: center;padding-bottom: 4upx;padding-left: 15upx;">删除</text> 
							</view>
						</uni-forms-item>
						<uni-forms-item label-width="150upx" label="资金来源" required>
							<uni-data-select v-model="item.value" :localdata="moneyList" @change="changeMoneyType(index,$event)"></uni-data-select>	
						</uni-forms-item>
						<uni-forms-item label-width="300upx" label="到位资金" required>
							<uni-easyinput type="number" v-model="item.fundInPlace" placeholder="请输入到位资金"/>		
						</uni-forms-item>
						<uni-forms-item label-width="300upx" label="完成投资" required>
							<uni-easyinput type="number" v-model="item.completionOfInvestment" placeholder="请输入完成投资"/>
						</uni-forms-item>
						<view class="line"></view>
					</view>
					 -->
					
					
					<uni-file-picker ref="pictureUp" :auto-upload="false" :del-icon="false" v-model="baseFormData.pic" limit="1" title="调度图片" @select="upLoadPic"></uni-file-picker>
				</uni-forms>
				<button type="primary" @click="putInfo" :disabled="flag">提交</button>
			</view>
	
	</view>
</template>

<script>
	import {uploadCommonFile} from '@/api/system/user.js'
	import { base64ToPath } from '@/node_modules/image-tools/index.js'
	import ImageCompressor from '@/node_modules/image-compressor.js'
	import {getMoneyList,putFormData,getProjectRecord,dataType} from '@/api/home/monthPut.js'
	
	export default {
		data() {
			return {
				projectList: [
					{
					value: 0,
					text: "数据获取中。。。",
					},
				],
				// 图片是否正在上传
				image: 0,
				// 提交标识
				flag: false,
				// 输入框样式
				styles: {
					disableColor: '#fff'
				},
				// 今天的日期
				today: '',
				// 项目信息
				projectData: {
					
					projectName: '',
				},
				baseFormData: {
					Name: this.$store.state.user.nickname,
					// 项目id
					id: '',
					// 备注
					remark: '',
					projectEnd:false,
					// 开工情况
					projectInfo:310,
					jzbqljwctz:0,
					dnljwctz:0,
					dntzwcl:0,
					planPeopleNum: null,
					realPeopleNum: null,
					pic: [],
					projectImplementationFundList: [],
					personMoney: null,
				},
				jzbqljwctz:0,
				dnljwctz:0,
				dntzwcl:0,
				// 资金类型
				moneyList: [],
				rules: {
				},
			}
		},
		 watch:{
			'baseFormData.completionOfInvestment'(newVal, oldVal) {
			  if(!this.baseFormData.completionOfInvestment){
				this.baseFormData.jzbqljwctz = structuredClone(this.jzbqljwctz);
				this.baseFormData.dntzwcl = structuredClone(this.dntzwcl)
				this.baseFormData.dnljwctz = structuredClone(this.dnljwctz)
			  }else if(!this.disabled && this.baseFormData.completionOfInvestment ) {
				this.baseFormData.jzbqljwctz = Number(this.jzbqljwctz) + Number(this.baseFormData.completionOfInvestment)
				this.baseFormData.dnljwctz = Number(this.dnljwctz) + Number(this.baseFormData.completionOfInvestment)
				let str2 = Number(this.baseFormData.bndjhwctzje);
				let dntzwcl =( Number(this.baseFormData.dnljwctz) / str2 ) * 100
				this.baseFormData.dntzwcl = dntzwcl.toFixed(2)
			  }
			},
		  },
		onLoad(e) {
			this.projectData = JSON.parse(e.info)
			let str = new Date().toISOString()
			this.today = str.slice(0, 10)
			this.baseFormData.id = e.id;
			this.getList();
			this.handleUpdate(e);
			// // 获取资金类型
			// getMoneyList('source_of_funds').then(res=>{
			// 	console.log(res)
			// 	this.moneyList = res.data
			// }).catch(err=>{
			// 	console.log(err)
			// })
		},
		methods: {
			clove(e){
				this.baseFormData.projectEnd=e
			},
			getList(){
				this.projectList = []
				dataType('operation_status').then(res=>{
					res.data.forEach((item,index)=>{
						let str = {
							value: item.dictLabel,
							text: item.dictLabel,
						}
						this.projectList.push(str)
					})
					
				}).catch(err=>{
					console.log(err)
				})
				
			},
			// 查详情
			handleUpdate(row) {
				const id = row.id
				getProjectRecord(id).then(response => {
					console.log('获取详情数据',response.data)
					this.baseFormData = response.data;
					this.baseFormData.Name= this.$store.state.user.nickname;
					this.baseFormData.bndjhwctzje = response.data.bndjhwctzje;
					this.baseFormData.projectEnd = response.data.projectEnd || false;
					this.baseFormData.projectInfo = response.data.projectInfo;
					console.log('dia',this.baseFormData.projectInfo )
					this.baseFormData.jzbqljwctz= response.data.jzbqljwctz || 0
					this.baseFormData.dnljwctz = response.data.dnljwctz || 0
					this.baseFormData.dntzwcl =response.data.dntzwcl || 0;
					this.jzbqljwctz = structuredClone(response.data.jzbqljwctz? response.data.jzbqljwctz : 0);
					this.dnljwctz = structuredClone(response.data.dnljwctz? response.data.dnljwctz : 0);
					this.dntzwcl = structuredClone(response.data.dntzwcl? response.data.dntzwcl : 0);
				});
			},
			// 增加一条资金信息
			// addMoney() {
			// 	const n = {
			// 		value: 0,
			// 		applyProjectId: this.projectData.id,
			// 		sourceOfFunds: '',
			// 		fundInPlace: '',
			// 		completionOfInvestment: '',
			// 	}
				
			// 	this.baseFormData.projectImplementationFundList.push(n)
			// },
			// 删除一条资金信息
			// delMoney(i) {
			// 	this.baseFormData.projectImplementationFundList.splice(i,1)
			// },
			// 选择资金类型
			// changeMoneyType(index,e) {
			// 	this.baseFormData.projectImplementationFundList[index].sourceOfFunds = this.moneyList[e-1].id
			// },
			// 上传图片
			upLoadPic(e) {
				// 触发上传图片
				this.image = this.image + 1
				
				const tempFilePaths = e.tempFilePaths;
				console.log(tempFilePaths)
				//获取图片临时路径
				const imgUrl=tempFilePaths[0]
				let that = this
				
					this.httpRequest(imgUrl).then(res=>{
						console.log(res,'成功转换为blob文件');
						let that1 = that
						new ImageCompressor(
							res,  // 需要压缩的图片文件
						    {
						        quality: 0.4, // 压缩的比率
						        success: function(res) {
						      	//  res 转换之后的文件流
						      	// 压缩成功之后默认是blob文件，那么这里采用原生的文件转换，其实就是新创建一个文件
						        // let files = new window.File([res], res.name, { type: res.type })
										console.log('压缩成功！')
										// 现在得到的就是标准的文件流，可以直接上传服务器  
										// 当然如果服务器默认接受blob文件，那就当上面描述的都是废话可自动删除、或注释掉
										
										// 保存压缩后的图片路径
										let img = URL.createObjectURL(res)
										
										setTimeout(()=>{
											let data = {name: 'file', filePath: img}
											uploadCommonFile(data).then(response => {
												const pic = {
													url: response.url,
													extname: 'png',
													name: 'picture'		
												}
												that1.baseFormData.pic.push(pic)
												
												console.log('成功上传图片！')
												that1.image = that1.image - 1
											}).catch(err=>{
												console.log('图片上传失败!',err)
												that1.image = that1.image - 1
											})
										},100)
						        },
								error(e) {
									that1.image = that1.image - 1
									console.log(e);
								}
						    }
						)
					}).catch(err=>{
						console.log(err)
						that.image = that.image - 1
					})
				
			},
			// 等待图片上传函数
			waitImg(){
				return new Promise((resolve,reject)=>{
					if(this.image > 0){
						setTimeout((res)=>{
							this.waitImg().then(()=>{
								resolve()
							})
						},100)
					}else{
						resolve()
					}
				})
			},
			// h5 - 图片压缩方法
			h5_compressImage(src) {
			  let _this = this;
			  uni.getImageInfo({
			    src,
			    success(res) {
			      console.log('压缩前', res);
			      let canvasWidth = res.width; // 图片原始宽度
			      let canvasHeight = res.height; // 图片原始高度
			      console.log('宽度-',canvasWidth,'高度-',canvasHeight);
			      let img = new Image();
			      img.src = res.path;
			      let canvas = document.createElement('canvas');
			      let ctx = canvas.getContext('2d');
			      // 这里根据要求限制图片宽高
			      if (canvasWidth >= 1500) {
			        canvas.width = canvasWidth * .1;
			      } else {
			        canvas.width = canvasWidth;
			      }
			      if (canvasHeight >= 1500) {
			        canvas.height = canvasHeight * .1;
			      } else {
			        canvas.height = canvasHeight;
			      }
			      ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
			      //toBlob()方法创造Blob对象，用以展示canvas上的图片
			      canvas.toBlob(function(fileSrc) {
			        let imgSrc = window.URL.createObjectURL(fileSrc);
			        console.log('压缩后的blob路径', imgSrc);
			        // 调用httpRequest方法，把bloburl转换成blob文件
			        _this.httpRequest(imgSrc).then(res => {
			            console.log(res,'成功转换为blob文件');
			            _this.h5_filetobase64(res); // 调用方法，把blob文件转换成base64图片
			        })
			      });
			    }
			  });
			},
			  
			// 传入blob路径，.then()获取blob文件
			httpRequest(src) {
			    let that = this
			    return new Promise((resolve, reject) => {
			      let xhr = new XMLHttpRequest()
			      xhr.open('GET', src, true)
			      xhr.responseType = 'blob'
			      xhr.onload = function (e) {
			          if (this.status == 200) {
			             let myBlob = this.response
			             let files = new window.File(
			                [myBlob],
			                myBlob.type,
			                { type: myBlob.type }
			             ) // myBlob.type 自定义文件名
			                resolve(files)
			             } else {
			                reject(false)
			             }
			          }
			      xhr.send()
			    })
			},
			  
			// h5 - 'blob文件'转base64图片的方法
			h5_filetobase64(file) {
				let that = this
			  const fileReader = new FileReader()
			  fileReader.readAsDataURL(file) // 读取blob类型的图像文件（不是blob路径），读取成功触发onload方法，并得到result（base64格式的图片）
			  fileReader.onload = (event) => {
					console.log(event)
			    console.log(fileReader.result,'fileReader.result - base64格式');
			    base64ToPath(fileReader.result).then(path=>
			    {
			    	that.image = path
			    	console.log('图片信息',path)
			    	
			    }).catch(err=>{
			    	console.log('导出图片失败', err)
			    })
			  }
			},
			// 提交数据
			putInfo() {
				uni.showLoading({
					title: '信息提交中...',
					mask: true
				})
				this.flag = true
				
				this.waitImg().then(()=>{
						// 提交信息
						this.$refs.baseForm.validate().then(res=>{
							console.log('表单数据信息：', res);
								console.log(JSON.stringify(this.baseFormData))
								
								putFormData(this.baseFormData).then(response => {
									console.log(response)
									uni.hideLoading()
									this.$modal.msgSuccess("提交成功")
									
									setTimeout(()=>{
										this.$tab.reLaunch('/pages/index')
									},1000)
									
								}).catch(err=>{
									this.flag = false
									uni.hideLoading()
									console.log('提交失败',err)
								})
								
							}).catch(err =>{
								this.flag = false
								uni.hideLoading()
								console.log('表单错误信息：', err);
							})
				})
				
			},
		}
	}
</script>

<style>
	page {
		background-color: white;
	}
	
	.example {
	padding: 15upx;
	}
	
	.line {
		width: 100%;
		margin-top: 27upx;
		margin-bottom: 27upx;
		border-width: 2upx 0upx 0upx 0upx;
		border-style: solid;
		border-color: gainsboro;
	}
</style>