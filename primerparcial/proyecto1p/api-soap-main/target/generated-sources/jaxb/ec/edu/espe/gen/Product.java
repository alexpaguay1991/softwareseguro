//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package ec.edu.espe.gen;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para product complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="product">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         <element name="imageUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="stockQuantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="category" type="{http://www.example.com/demosoap/gen}category" minOccurs="0"/>
 *         <element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product", propOrder = {
    "id",
    "name",
    "description",
    "price",
    "imageUrl",
    "stockQuantity",
    "category",
    "createdAt",
    "updatedAt"
})
public class Product {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    protected String description;
    @XmlElement(required = true)
    protected BigDecimal price;
    protected String imageUrl;
    protected int stockQuantity;
    protected Category category;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedAt;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad price.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Define el valor de la propiedad price.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Obtiene el valor de la propiedad imageUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Define el valor de la propiedad imageUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad stockQuantity.
     * 
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Define el valor de la propiedad stockQuantity.
     * 
     */
    public void setStockQuantity(int value) {
        this.stockQuantity = value;
    }

    /**
     * Obtiene el valor de la propiedad category.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Define el valor de la propiedad category.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Obtiene el valor de la propiedad createdAt.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Define el valor de la propiedad createdAt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * Obtiene el valor de la propiedad updatedAt.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Define el valor de la propiedad updatedAt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedAt(XMLGregorianCalendar value) {
        this.updatedAt = value;
    }

}
