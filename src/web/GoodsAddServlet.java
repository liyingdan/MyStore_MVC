package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import domain.goods;
import lombok.Setter;
import service.GoodsService;

@WebServlet("/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Setter
	private String uploadFileName;  //文件名称
	@Setter
	private File upload;   //上传文件
	@Setter
	private String uploadContentType;  //文件类型 
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addddddddddddd");
       
		if(upload != null) {
			//上传文件
			//随机生成文件名称
			
			//1.获取文件扩展名  sss.jpg
			int index = uploadFileName.lastIndexOf(".");
			String etx = uploadFileName.substring(index);
			
			//2.随机生成文件名  拼接扩展名
			String uuid = UUID.randomUUID().toString();
			String uuidFileName = uuid.replace("-", "")+etx;
			//System.out.println(uuidFileName);
			
			//确定上传的路径
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			//拼接新文件路径
			File desFile = new File(path+"/"+uuidFileName);
			
			//文件上传
			FileUtils.copyFile(upload, desFile);
			
		}

		
		
		
		
		/*//普通添加信息步骤
		System.out.println("GoodsAddServlet");
		request.setCharacterEncoding("utf-8");
		//获取所有参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("parameterMap"+parameterMap);
		//把参数分装成对象（使用BeanUtils可以直接封装成对象，有两个参数，第一个参数是封装成什么对象，第二个是接收map）
		goods goods = new goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			//添加图片暂时这样写
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//调用服务层
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//跳转列表, 取出最新的数据GoodsListServlet
			request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		
	}
    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
        }

	private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

	
}
