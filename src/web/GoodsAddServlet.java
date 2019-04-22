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
	private String uploadFileName;  //�ļ�����
	@Setter
	private File upload;   //�ϴ��ļ�
	@Setter
	private String uploadContentType;  //�ļ����� 
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addddddddddddd");
       
		if(upload != null) {
			//�ϴ��ļ�
			//��������ļ�����
			
			//1.��ȡ�ļ���չ��  sss.jpg
			int index = uploadFileName.lastIndexOf(".");
			String etx = uploadFileName.substring(index);
			
			//2.��������ļ���  ƴ����չ��
			String uuid = UUID.randomUUID().toString();
			String uuidFileName = uuid.replace("-", "")+etx;
			//System.out.println(uuidFileName);
			
			//ȷ���ϴ���·��
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			//ƴ�����ļ�·��
			File desFile = new File(path+"/"+uuidFileName);
			
			//�ļ��ϴ�
			FileUtils.copyFile(upload, desFile);
			
		}

		
		
		
		
		/*//��ͨ�����Ϣ����
		System.out.println("GoodsAddServlet");
		request.setCharacterEncoding("utf-8");
		//��ȡ���в���
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("parameterMap"+parameterMap);
		//�Ѳ�����װ�ɶ���ʹ��BeanUtils����ֱ�ӷ�װ�ɶ�����������������һ�������Ƿ�װ��ʲô���󣬵ڶ����ǽ���map��
		goods goods = new goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			//���ͼƬ��ʱ����д
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//���÷����
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//��ת�б�, ȡ�����µ�����GoodsListServlet
			request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		
	}
    private String makeFileName(String filename){  //2.jpg
        //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
        return UUID.randomUUID().toString() + "_" + filename;
        }

	private String makePath(String filename,String savePath){
        //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //�����µı���Ŀ¼
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
        File file = new File(dir);
        //���Ŀ¼������
        if(!file.exists()){
            //����Ŀ¼
            file.mkdirs();
        }
        return dir;
    }

	
}
