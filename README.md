# PrimefacesLazyImplement
Primefaces lazy data model(Hibernate ile) implement edilmesi.LazyDataModel hibernate 5.2.10 implement
Deprecated createCriteria method in Hibernate 5
Hibernate 5 ile createCriteria() metodu kaldırıldı. Güncel hali ile bu projede implement edildi.

Hibernate 5 usage/Kullanımı
CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
CriteriaQuery<ZzAtakanPerson> critQuery = criteriaBuilder.createQuery(ZzAtakanPerson.class);
...
Konu anlatımı : http://www.atakancoban.com/index.php/2017/09/12/lazydatamodel-kullanimi/

## UNUTMA 
Projei indirdiğiniz zaman **testcon.xml** ve **hibernate.cfg.xml**içindeki connection bilgilerinizi güncelleyiniz.

### VERSION
``` 
# Java Version       : 1.8
# Spring Version     : 4.3.10.RELEASE
# Hibernate Version  : 5.2.10.Final
# Log4j Version      : 1.2.17
# Oracle Version     : 11G
# Oracle Jdbc        : 6
# JSF Version        : 2.2
# Dynamic Web Module : 3.1

```

```markdown
   Copyright 2017 Atakan Çoban

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
