package una.force_gym.report;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import una.force_gym.dto.ProductInventoryDTO;

@Component
public class ProductInventoryPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // Obtener los productos desde el modelo
        @SuppressWarnings("unchecked")
        List<ProductInventoryDTO> productos = (List<ProductInventoryDTO>) model.get("productos");
        
        // Abrir el documento PDF
        document.open();

        // Agregar un título al PDF
        document.add(new Paragraph("Listado de Productos de Inventario"));

        // Crear la tabla con 5 columnas
        PdfPTable table = new PdfPTable(5);
        
        // Añadir encabezados de columna
        table.addCell("ID Producto");
        table.addCell("Código");
        table.addCell("Nombre");
        table.addCell("Costo");
        table.addCell("Cantidad");

        // Llenar la tabla con los datos de los productos
        for (ProductInventoryDTO product : productos) {
            table.addCell(String.valueOf(product.getIdProductInventory()));  // ID
            table.addCell(product.getCode());  // Código
            table.addCell(product.getName());  // Nombre
            table.addCell(String.valueOf(product.getCost()));  // Costo
            table.addCell(String.valueOf(product.getQuantity()));  // Cantidad
        }

        // Agregar la tabla al documento
        document.add(table);

        document.close();
    }
}
