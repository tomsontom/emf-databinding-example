/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedEClassImpl.java,v 1.24 2008/07/13 13:12:49 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;
import org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride;
import org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue;
import org.eclipse.emf.teneo.annotations.pannotation.Embeddable;
import org.eclipse.emf.teneo.annotations.pannotation.Entity;
import org.eclipse.emf.teneo.annotations.pannotation.IdClass;
import org.eclipse.emf.teneo.annotations.pannotation.Inheritance;
import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass;
import org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable;
import org.eclipse.emf.teneo.annotations.pannotation.Table;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PAnnotated EClass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getPaEPackage <em>Pa EPackage</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getModelEClass <em>Model EClass</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getPaEStructuralFeatures <em>Pa EStructural Features</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getAttributeOverrides <em>Attribute Overrides</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getDiscriminatorColumn <em>Discriminator Column</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getDiscriminatorValue <em>Discriminator Value</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getEmbeddable <em>Embeddable</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getMappedSuperclass <em>Mapped Superclass</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getIdClass <em>Id Class</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getInheritance <em>Inheritance</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getPrimaryKeyJoinColumns <em>Primary Key Join Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getSecondaryTables <em>Secondary Tables</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getTableGenerator <em>Table Generator</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl#getAssociationOverrides <em>Association Overrides</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PAnnotatedEClassImpl extends PAnnotatedEModelElementImpl implements PAnnotatedEClass {
	/**
	 * The cached value of the '{@link #getPaEStructuralFeatures() <em>Pa EStructural Features</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPaEStructuralFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<PAnnotatedEStructuralFeature> paEStructuralFeatures;

	/**
	 * The cached value of the '{@link #getAttributeOverrides() <em>Attribute Overrides</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAttributeOverrides()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeOverride> attributeOverrides;

	/**
	 * The cached value of the '{@link #getDiscriminatorColumn() <em>Discriminator Column</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiscriminatorColumn()
	 * @generated
	 * @ordered
	 */
	protected DiscriminatorColumn discriminatorColumn;

	/**
	 * The cached value of the '{@link #getDiscriminatorValue() <em>Discriminator Value</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiscriminatorValue()
	 * @generated
	 * @ordered
	 */
	protected DiscriminatorValue discriminatorValue;

	/**
	 * The cached value of the '{@link #getEmbeddable() <em>Embeddable</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEmbeddable()
	 * @generated
	 * @ordered
	 */
	protected Embeddable embeddable;

	/**
	 * The cached value of the '{@link #getMappedSuperclass() <em>Mapped Superclass</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMappedSuperclass()
	 * @generated
	 * @ordered
	 */
	protected MappedSuperclass mappedSuperclass;

	/**
	 * The cached value of the '{@link #getEntity() <em>Entity</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEntity()
	 * @generated
	 * @ordered
	 */
	protected Entity entity;

	/**
	 * The cached value of the '{@link #getIdClass() <em>Id Class</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIdClass()
	 * @generated
	 * @ordered
	 */
	protected IdClass idClass;

	/**
	 * The cached value of the '{@link #getInheritance() <em>Inheritance</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected Inheritance inheritance;

	/**
	 * The cached value of the '{@link #getPrimaryKeyJoinColumns() <em>Primary Key Join Columns</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPrimaryKeyJoinColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<PrimaryKeyJoinColumn> primaryKeyJoinColumns;

	/**
	 * The cached value of the '{@link #getSecondaryTables() <em>Secondary Tables</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSecondaryTables()
	 * @generated
	 * @ordered
	 */
	protected EList<SecondaryTable> secondaryTables;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table;

	/**
	 * The cached value of the '{@link #getTableGenerator() <em>Table Generator</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTableGenerator()
	 * @generated
	 * @ordered
	 */
	protected TableGenerator tableGenerator;

	/**
	 * The cached value of the '{@link #getAssociationOverrides() <em>Association Overrides</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAssociationOverrides()
	 * @generated
	 * @ordered
	 */
	protected EList<AssociationOverride> associationOverrides;

	/** The computed super pa entity */
	private PAnnotatedEClass paSuperEntity = null;

	/** Map this type as an entity or as a concrete class */
	private boolean mapAsEntity = true;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PAnnotatedEClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PamodelPackage.Literals.PANNOTATED_ECLASS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEPackage getPaEPackage() {
		if (eContainerFeatureID != PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE) return null;
		return (PAnnotatedEPackage)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPaEPackage(PAnnotatedEPackage newPaEPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPaEPackage, PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaEPackage(PAnnotatedEPackage newPaEPackage) {
		if (newPaEPackage != eInternalContainer() || (eContainerFeatureID != PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE && newPaEPackage != null)) {
			if (EcoreUtil.isAncestor(this, newPaEPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPaEPackage != null)
				msgs = ((InternalEObject)newPaEPackage).eInverseAdd(this, PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES, PAnnotatedEPackage.class, msgs);
			msgs = basicSetPaEPackage(newPaEPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE, newPaEPackage, newPaEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelEClass() {
		EClass modelEClass = basicGetModelEClass();
		return modelEClass != null && modelEClass.eIsProxy() ? (EClass)eResolveProxy((InternalEObject)modelEClass) : modelEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EClass basicGetModelEClass() {
		return (EClass) getModelElement();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PAnnotatedEStructuralFeature> getPaEStructuralFeatures() {
		if (paEStructuralFeatures == null) {
			paEStructuralFeatures = new EObjectContainmentWithInverseEList<PAnnotatedEStructuralFeature>(PAnnotatedEStructuralFeature.class, this, PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES, PamodelPackage.PANNOTATED_ESTRUCTURAL_FEATURE__PA_ECLASS);
		}
		return paEStructuralFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeOverride> getAttributeOverrides() {
		if (attributeOverrides == null) {
			attributeOverrides = new EObjectContainmentEList<AttributeOverride>(AttributeOverride.class, this, PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES);
		}
		return attributeOverrides;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DiscriminatorColumn getDiscriminatorColumn() {
		return discriminatorColumn;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiscriminatorColumn(DiscriminatorColumn newDiscriminatorColumn,
			NotificationChain msgs) {
		DiscriminatorColumn oldDiscriminatorColumn = discriminatorColumn;
		discriminatorColumn = newDiscriminatorColumn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN, oldDiscriminatorColumn, newDiscriminatorColumn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiscriminatorColumn(DiscriminatorColumn newDiscriminatorColumn) {
		if (newDiscriminatorColumn != discriminatorColumn) {
			NotificationChain msgs = null;
			if (discriminatorColumn != null)
				msgs = ((InternalEObject)discriminatorColumn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN, null, msgs);
			if (newDiscriminatorColumn != null)
				msgs = ((InternalEObject)newDiscriminatorColumn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN, null, msgs);
			msgs = basicSetDiscriminatorColumn(newDiscriminatorColumn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN, newDiscriminatorColumn, newDiscriminatorColumn));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DiscriminatorValue getDiscriminatorValue() {
		return discriminatorValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiscriminatorValue(DiscriminatorValue newDiscriminatorValue, NotificationChain msgs) {
		DiscriminatorValue oldDiscriminatorValue = discriminatorValue;
		discriminatorValue = newDiscriminatorValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE, oldDiscriminatorValue, newDiscriminatorValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiscriminatorValue(DiscriminatorValue newDiscriminatorValue) {
		if (newDiscriminatorValue != discriminatorValue) {
			NotificationChain msgs = null;
			if (discriminatorValue != null)
				msgs = ((InternalEObject)discriminatorValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE, null, msgs);
			if (newDiscriminatorValue != null)
				msgs = ((InternalEObject)newDiscriminatorValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE, null, msgs);
			msgs = basicSetDiscriminatorValue(newDiscriminatorValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE, newDiscriminatorValue, newDiscriminatorValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Embeddable getEmbeddable() {
		return embeddable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEmbeddable(Embeddable newEmbeddable, NotificationChain msgs) {
		Embeddable oldEmbeddable = embeddable;
		embeddable = newEmbeddable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE, oldEmbeddable, newEmbeddable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmbeddable(Embeddable newEmbeddable) {
		if (newEmbeddable != embeddable) {
			NotificationChain msgs = null;
			if (embeddable != null)
				msgs = ((InternalEObject)embeddable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE, null, msgs);
			if (newEmbeddable != null)
				msgs = ((InternalEObject)newEmbeddable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE, null, msgs);
			msgs = basicSetEmbeddable(newEmbeddable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE, newEmbeddable, newEmbeddable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MappedSuperclass getMappedSuperclass() {
		return mappedSuperclass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMappedSuperclass(MappedSuperclass newMappedSuperclass, NotificationChain msgs) {
		MappedSuperclass oldMappedSuperclass = mappedSuperclass;
		mappedSuperclass = newMappedSuperclass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS, oldMappedSuperclass, newMappedSuperclass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedSuperclass(MappedSuperclass newMappedSuperclass) {
		if (newMappedSuperclass != mappedSuperclass) {
			NotificationChain msgs = null;
			if (mappedSuperclass != null)
				msgs = ((InternalEObject)mappedSuperclass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS, null, msgs);
			if (newMappedSuperclass != null)
				msgs = ((InternalEObject)newMappedSuperclass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS, null, msgs);
			msgs = basicSetMappedSuperclass(newMappedSuperclass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS, newMappedSuperclass, newMappedSuperclass));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEntity(Entity newEntity, NotificationChain msgs) {
		Entity oldEntity = entity;
		entity = newEntity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__ENTITY, oldEntity, newEntity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(Entity newEntity) {
		if (newEntity != entity) {
			NotificationChain msgs = null;
			if (entity != null)
				msgs = ((InternalEObject)entity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__ENTITY, null, msgs);
			if (newEntity != null)
				msgs = ((InternalEObject)newEntity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__ENTITY, null, msgs);
			msgs = basicSetEntity(newEntity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__ENTITY, newEntity, newEntity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IdClass getIdClass() {
		return idClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdClass(IdClass newIdClass, NotificationChain msgs) {
		IdClass oldIdClass = idClass;
		idClass = newIdClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__ID_CLASS, oldIdClass, newIdClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdClass(IdClass newIdClass) {
		if (newIdClass != idClass) {
			NotificationChain msgs = null;
			if (idClass != null)
				msgs = ((InternalEObject)idClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__ID_CLASS, null, msgs);
			if (newIdClass != null)
				msgs = ((InternalEObject)newIdClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__ID_CLASS, null, msgs);
			msgs = basicSetIdClass(newIdClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__ID_CLASS, newIdClass, newIdClass));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Inheritance getInheritance() {
		return inheritance;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInheritance(Inheritance newInheritance, NotificationChain msgs) {
		Inheritance oldInheritance = inheritance;
		inheritance = newInheritance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__INHERITANCE, oldInheritance, newInheritance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritance(Inheritance newInheritance) {
		if (newInheritance != inheritance) {
			NotificationChain msgs = null;
			if (inheritance != null)
				msgs = ((InternalEObject)inheritance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__INHERITANCE, null, msgs);
			if (newInheritance != null)
				msgs = ((InternalEObject)newInheritance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__INHERITANCE, null, msgs);
			msgs = basicSetInheritance(newInheritance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__INHERITANCE, newInheritance, newInheritance));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrimaryKeyJoinColumn> getPrimaryKeyJoinColumns() {
		if (primaryKeyJoinColumns == null) {
			primaryKeyJoinColumns = new EObjectContainmentEList<PrimaryKeyJoinColumn>(PrimaryKeyJoinColumn.class, this, PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS);
		}
		return primaryKeyJoinColumns;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SecondaryTable> getSecondaryTables() {
		if (secondaryTables == null) {
			secondaryTables = new EObjectContainmentEList<SecondaryTable>(SecondaryTable.class, this, PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES);
		}
		return secondaryTables;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(Table newTable, NotificationChain msgs) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__TABLE, oldTable, newTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		if (newTable != table) {
			NotificationChain msgs = null;
			if (table != null)
				msgs = ((InternalEObject)table).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__TABLE, null, msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__TABLE, null, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TableGenerator getTableGenerator() {
		return tableGenerator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTableGenerator(TableGenerator newTableGenerator, NotificationChain msgs) {
		TableGenerator oldTableGenerator = tableGenerator;
		tableGenerator = newTableGenerator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR, oldTableGenerator, newTableGenerator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableGenerator(TableGenerator newTableGenerator) {
		if (newTableGenerator != tableGenerator) {
			NotificationChain msgs = null;
			if (tableGenerator != null)
				msgs = ((InternalEObject)tableGenerator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR, null, msgs);
			if (newTableGenerator != null)
				msgs = ((InternalEObject)newTableGenerator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR, null, msgs);
			msgs = basicSetTableGenerator(newTableGenerator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR, newTableGenerator, newTableGenerator));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationOverride> getAssociationOverrides() {
		if (associationOverrides == null) {
			associationOverrides = new EObjectContainmentEList<AssociationOverride>(AssociationOverride.class, this, PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES);
		}
		return associationOverrides;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPaEPackage((PAnnotatedEPackage)otherEnd, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPaEStructuralFeatures()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				return basicSetPaEPackage(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				return ((InternalEList<?>)getPaEStructuralFeatures()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES:
				return ((InternalEList<?>)getAttributeOverrides()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN:
				return basicSetDiscriminatorColumn(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE:
				return basicSetDiscriminatorValue(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE:
				return basicSetEmbeddable(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS:
				return basicSetMappedSuperclass(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__ENTITY:
				return basicSetEntity(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__ID_CLASS:
				return basicSetIdClass(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__INHERITANCE:
				return basicSetInheritance(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS:
				return ((InternalEList<?>)getPrimaryKeyJoinColumns()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES:
				return ((InternalEList<?>)getSecondaryTables()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__TABLE:
				return basicSetTable(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR:
				return basicSetTableGenerator(null, msgs);
			case PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES:
				return ((InternalEList<?>)getAssociationOverrides()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				return eInternalContainer().eInverseRemove(this, PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES, PAnnotatedEPackage.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				return getPaEPackage();
			case PamodelPackage.PANNOTATED_ECLASS__MODEL_ECLASS:
				if (resolve) return getModelEClass();
				return basicGetModelEClass();
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				return getPaEStructuralFeatures();
			case PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES:
				return getAttributeOverrides();
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN:
				return getDiscriminatorColumn();
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE:
				return getDiscriminatorValue();
			case PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE:
				return getEmbeddable();
			case PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS:
				return getMappedSuperclass();
			case PamodelPackage.PANNOTATED_ECLASS__ENTITY:
				return getEntity();
			case PamodelPackage.PANNOTATED_ECLASS__ID_CLASS:
				return getIdClass();
			case PamodelPackage.PANNOTATED_ECLASS__INHERITANCE:
				return getInheritance();
			case PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS:
				return getPrimaryKeyJoinColumns();
			case PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES:
				return getSecondaryTables();
			case PamodelPackage.PANNOTATED_ECLASS__TABLE:
				return getTable();
			case PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR:
				return getTableGenerator();
			case PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES:
				return getAssociationOverrides();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				setPaEPackage((PAnnotatedEPackage)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				getPaEStructuralFeatures().clear();
				getPaEStructuralFeatures().addAll((Collection<? extends PAnnotatedEStructuralFeature>)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES:
				getAttributeOverrides().clear();
				getAttributeOverrides().addAll((Collection<? extends AttributeOverride>)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN:
				setDiscriminatorColumn((DiscriminatorColumn)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE:
				setDiscriminatorValue((DiscriminatorValue)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE:
				setEmbeddable((Embeddable)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS:
				setMappedSuperclass((MappedSuperclass)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ENTITY:
				setEntity((Entity)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ID_CLASS:
				setIdClass((IdClass)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__INHERITANCE:
				setInheritance((Inheritance)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS:
				getPrimaryKeyJoinColumns().clear();
				getPrimaryKeyJoinColumns().addAll((Collection<? extends PrimaryKeyJoinColumn>)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES:
				getSecondaryTables().clear();
				getSecondaryTables().addAll((Collection<? extends SecondaryTable>)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__TABLE:
				setTable((Table)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR:
				setTableGenerator((TableGenerator)newValue);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES:
				getAssociationOverrides().clear();
				getAssociationOverrides().addAll((Collection<? extends AssociationOverride>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				setPaEPackage((PAnnotatedEPackage)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				getPaEStructuralFeatures().clear();
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES:
				getAttributeOverrides().clear();
				return;
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN:
				setDiscriminatorColumn((DiscriminatorColumn)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE:
				setDiscriminatorValue((DiscriminatorValue)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE:
				setEmbeddable((Embeddable)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS:
				setMappedSuperclass((MappedSuperclass)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ENTITY:
				setEntity((Entity)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ID_CLASS:
				setIdClass((IdClass)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__INHERITANCE:
				setInheritance((Inheritance)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS:
				getPrimaryKeyJoinColumns().clear();
				return;
			case PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES:
				getSecondaryTables().clear();
				return;
			case PamodelPackage.PANNOTATED_ECLASS__TABLE:
				setTable((Table)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR:
				setTableGenerator((TableGenerator)null);
				return;
			case PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES:
				getAssociationOverrides().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE:
				return getPaEPackage() != null;
			case PamodelPackage.PANNOTATED_ECLASS__MODEL_ECLASS:
				return basicGetModelEClass() != null;
			case PamodelPackage.PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES:
				return paEStructuralFeatures != null && !paEStructuralFeatures.isEmpty();
			case PamodelPackage.PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES:
				return attributeOverrides != null && !attributeOverrides.isEmpty();
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN:
				return discriminatorColumn != null;
			case PamodelPackage.PANNOTATED_ECLASS__DISCRIMINATOR_VALUE:
				return discriminatorValue != null;
			case PamodelPackage.PANNOTATED_ECLASS__EMBEDDABLE:
				return embeddable != null;
			case PamodelPackage.PANNOTATED_ECLASS__MAPPED_SUPERCLASS:
				return mappedSuperclass != null;
			case PamodelPackage.PANNOTATED_ECLASS__ENTITY:
				return entity != null;
			case PamodelPackage.PANNOTATED_ECLASS__ID_CLASS:
				return idClass != null;
			case PamodelPackage.PANNOTATED_ECLASS__INHERITANCE:
				return inheritance != null;
			case PamodelPackage.PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS:
				return primaryKeyJoinColumns != null && !primaryKeyJoinColumns.isEmpty();
			case PamodelPackage.PANNOTATED_ECLASS__SECONDARY_TABLES:
				return secondaryTables != null && !secondaryTables.isEmpty();
			case PamodelPackage.PANNOTATED_ECLASS__TABLE:
				return table != null;
			case PamodelPackage.PANNOTATED_ECLASS__TABLE_GENERATOR:
				return tableGenerator != null;
			case PamodelPackage.PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES:
				return associationOverrides != null && !associationOverrides.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @return true if the eclass or its ancestor has an PAnnotatedEStructuralFeature with an id
	 *         annotation.
	 */
	public boolean hasIdAnnotatedFeature() {
		if (!getPaIdFeatures().isEmpty()) {
			return true;
		}
		if (getPaSuperEntity() != null && getPaSuperEntity().hasIdAnnotatedFeature()) {
			return true;
		}

		// test if the id is got from a mapped superclass
		final Iterator<EClass> i = getModelEClass().getESuperTypes().iterator();
		while (i.hasNext()) {
			PAnnotatedEClass x = getPaModel().getPAnnotated(i.next());
			if (x.hasIdAnnotatedFeature() && x.getMappedSuperclass() != null) {
				return true;
			}
		}

		return false;
	}

	/** Returns true if the eclass has an PAnnotatedEStructuralFeature with an version annotation */
	public boolean hasVersionAnnotatedFeature() {
		for (PAnnotatedEStructuralFeature aFeature : getPaEStructuralFeatures()) {
			if (aFeature instanceof PAnnotatedEAttribute) {
				if (((PAnnotatedEAttribute) aFeature).getVersion() != null) {
					return true;
				}
			}
		}
		if (getPaSuperEntity() != null) {
			return getPaSuperEntity().hasVersionAnnotatedFeature();
		}
		return false;
	}

	/**
	 * {@inheritDoc }
	 */
	public PAnnotatedModel getPaModel() {
		PAnnotatedEPackage ePackage = getPaEPackage();
		return ePackage != null ? ePackage.getPaModel() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PAnnotatedEStructuralFeature> getPaIdFeatures() {
		if (getPaSuperEntity() != null) {
			return getPaSuperEntity().getPaIdFeatures();
		}
		// TODO cache this list
		final List<PAnnotatedEStructuralFeature> attrs = new ArrayList<PAnnotatedEStructuralFeature>();
		for (PAnnotatedEStructuralFeature aFeature : getPaEStructuralFeatures()) {
			if (aFeature instanceof PAnnotatedEAttribute) {
				if (((PAnnotatedEAttribute) aFeature).getId() != null &&
						((PAnnotatedEAttribute) aFeature).getTransient() == null) {
					attrs.add(aFeature);
				}
			}
			if (aFeature instanceof PAnnotatedEReference) {
				if (((PAnnotatedEReference) aFeature).getEmbeddedId() != null &&
						((PAnnotatedEReference) aFeature).getTransient() == null) {
					attrs.add(aFeature);
				}
			}
		}
		return attrs;
	}

	/**
	 * {@inheritDoc }
	 */
	public List<PAnnotatedEClass> getPaMappedSupers() {
		// TODO cache its MappedSuperClass
		PAnnotatedModel model = getPaModel();
		List<PAnnotatedEClass> mappedSupers = new ArrayList<PAnnotatedEClass>();
		if (model != null && getModelEClass() != null) {
			Iterator<EClass> i = getModelEClass().getESuperTypes().iterator();
			while (i.hasNext()) {
				PAnnotatedEClass x = model.getPAnnotated(i.next());
				if (x.getMappedSuperclass() != null) {
					mappedSupers.add(x);
				}
			}
		}
		return mappedSupers;
	}

	/**
	 * {@inheritDoc }
	 */
	public PAnnotatedEClass getPaSuperEntity() {
		return paSuperEntity;
	}

	/**
	 * @param paSuperEntity
	 *            the paSuperEntity to set
	 */
	public void setPaSuperEntity(PAnnotatedEClass paSuperEntity) {
		this.paSuperEntity = paSuperEntity;
	}

	/**
	 * {@inheritDoc }
	 */
	public InheritanceType getInheritanceStrategy() {
		// TODO cache it
		if (getInheritance() != null) {
			return getInheritance().getStrategy();
		}
		PAnnotatedEClass paSuperEntity = getPaSuperEntity();
		return paSuperEntity != null ? paSuperEntity.getInheritanceStrategy() : InheritanceType.SINGLE_TABLE;
	}

	/** returns true if this EClass should be mapped as an entity or as a concrete class */
	public boolean isOnlyMapAsEntity() {
		return mapAsEntity;
	}

	/** sets if this eclass should be mapped as an entity */
	public void setOnlyMapAsEntity(boolean mapAsEntity) {
		this.mapAsEntity = mapAsEntity;
	}
} // PAnnotatedEClassImpl
