package braynstorm.commonlib.math;

public class Ray {
    
    private Vector3f origin;
    private Vector3f direction;
    
    public Ray(Vector3f origin, Vector3f direction){
        this.origin = origin;
        this.direction = direction;
    }
    
    /**
     * Retruns null if it doesn't intersect
     * @param v0 triangle vertex
     * @param v1 triangle vertex
     * @param v2 triangle vertex
     * @param normal triangle nomal
     */
    public Vector3f intersect(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f normal){
        /*
         * Note to future-self: започват да се заформят доста трудни неща пред нас, 
         * за да разбера, къде е цъкнал играчът, трябва да правя рей-каст в/у всички триъгълници на сцената.
         * Възможно подобрение: първо един пик-фейз за да разберя в/у кой меш е цъкнал, и чак след това форийч на всички триъгълници от меша.
         * 
         * Всичко това е пър фрейм
         */
        
        float normalDotDirection = normal.dot(direction);
        
        if(Math.abs(normalDotDirection) < 0.00001f) // Parallel... no hits.
            return null;
        
        float distance = (normal.dot(origin) + normal.dot(v0)) / normalDotDirection;
        
        if(distance < 0)
            return null; // Triangle is behind the ray origin.
        
        /** Hit point with the triangle's <b>PLANE<b>. */
        Vector3f hit = new Vector3f(direction).mul(distance).add(origin);
        
        Vector3f edge;
        Vector3f vp;
        
        edge = v1.getSub(v0);
        vp = hit.getSub(v0);
        
        if(normal.dot(edge.cross(vp)) < 0) // On the 'right' side, not in the triangle
            return null;
        
        edge = v2.getSub(v1);
        vp = hit.getSub(v1);
        
        if(normal.dot(edge.cross(vp)) < 0) // On the 'right' side, not in the triangle
            return null;
        
        edge = v0.getSub(v2);
        vp = hit.getSub(v2);
        
        if(normal.dot(edge.cross(vp)) < 0) // On the 'right' side, not in the triangle
            return null;
        
        return hit;
    }
    
    /**
     * Conveniece {@link Ray#intersect(Vector3f, Vector3f, Vector3f)}. Computes the normal for you.
     * @param v0
     * @param v1
     * @param v2
     * @return The intersection point or null if they don't intersect
     */
    public Vector3f intersect(Vector3f v0, Vector3f v1, Vector3f v2){
        Vector3f v0v1 = v1.getSub(v0);
        Vector3f v0v2 = v2.getSub(v0);
        Vector3f normal = v0v1.cross(v0v2);
        
        return intersect(v0, v1, v2, normal);
    }
}
