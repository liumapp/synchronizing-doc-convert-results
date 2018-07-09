/**
 * @file doc-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<template>
  <div>
    <br>
    <Row>
      <Col span="8" offset="8">
        <Upload
          multiple
          type="drag"
          action="#"
          accept=".doc, .docx"
          :before-upload="handleFileToBase64"
          :default-file-list="readyUploadFile"
          :on-remove="handleRmFile">
          <div style="padding: 20px 0">
            <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
            <p>Click or drag files here to upload</p>
          </div>
        </Upload>
      </Col>
    </Row>
    <Row>
      <Col span="2" offset="14">
        <Button type="primary" @click="submitPic">提交</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'doc-upload',
  props: [
    'convertId'
  ],
  data () {
    return {
      fileList: [],
      readyUploadFile: [],
      cid: 0,
    }
  },
  created () {
    this.cid = this.convertId;
  },
  methods: {
    handleFileToBase64 (file) {
      let reader = new FileReader();
      let _vue = this;
      reader.readAsDataURL(file);
      reader.onload = function () {
        _vue.fileList.push({name: file.name, content: reader.result, convertId: _vue.cid});
        _vue.readyUploadFile.push({name: file.name});
      }
      return false;
    },
    handleRmFile (file) {
      console.log(file);
      let _vue = this;
      let i = 0;
      this.fileList.forEach(function (e) {
        if (e.name == file.name) {
          _vue.fileList.splice(i,1);
          _vue.readyUploadFile.splice(i,1);
        }
        i++;
      });
    },
    submitPic () {
      if (!this.isEmpty()) {
        util.post('upload/multybase64', this.fileList).then(res => {
          this.$Message.success('file upload success!');
          this.$emit('setDocData',this.handleDocList());
          this.$emit('next');
        });
      } else {
        this.$Message.error('请至少上传一个doc文件');
      }
    },
    isEmpty () {
      return this.fileList.length == 0;
    },
    /**
     * 0: waiting convert ; 1: converting success
     */
    handleDocList () {
      let docList = [];
      this.fileList.forEach(function (e) {
        docList.push({
          filename: e.name,
          convertId: e.convertId,
          status: 0
        })
      });
      return docList;
    }
  }
}
</script>
